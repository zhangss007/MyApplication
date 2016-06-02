/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package demo.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import demo.myapplication.adapter.NewsAdapter;
import demo.myapplication.bean.NewsBean;
import demo.myapplication.commoms.Urls;
import demo.myapplication.news.presenter.NewsPresenter;
import demo.myapplication.news.presenter.NewsPresenterImpl;
import demo.myapplication.news.view.NewView;
import demo.myapplication.news.widget.NewsDetailActivity;
import demo.myapplication.utils.LogUtils;

public class NewsListFragment extends Fragment implements NewView, SwipeRefreshLayout.OnRefreshListener{

	private  static final String TAG = "NewsListFragment";
	private static final String FRAGMENT_TYPE = "type";
	public static final int NEWS_TYPE_TOP = 0;
	public static final int NEWS_TYPE_NBA = 1;
	public static final int NEWS_TYPE_CARS = 2;
	public static final int NEWS_TYPE_JOKES = 3;

	private SwipeRefreshLayout mSwipeRefreshWidget;
	private RecyclerView mRecyclerView;
	private LinearLayoutManager mLayoutManager;
	private NewsAdapter mAdapter;
	private List<NewsBean> mData;

	private NewsPresenter mNewsPresenter;
	private int mType;
	private int mPageIndex= 0;

	public static NewsListFragment newInstance(int type) {
		NewsListFragment f = new NewsListFragment();
		Bundle b = new Bundle();
		b.putInt(FRAGMENT_TYPE, type);
		f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mNewsPresenter = new NewsPresenterImpl(this);
		mType = getArguments().getInt(FRAGMENT_TYPE);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_newslist,null);
		mSwipeRefreshWidget = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh_widget);
		mSwipeRefreshWidget.setColorSchemeResources(R.color.primary,
				R.color.primary_dark, R.color.primary_light,
				R.color.accent);
		mSwipeRefreshWidget.setOnRefreshListener(this);

		mRecyclerView = (RecyclerView)view.findViewById(R.id.recycle_view);
		mRecyclerView.setHasFixedSize(true);

		mLayoutManager = new LinearLayoutManager(getActivity());
		mRecyclerView.setLayoutManager(mLayoutManager);

		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mAdapter = new NewsAdapter(getActivity().getApplicationContext());
		mAdapter.setOnItemClickListener(mOnItemClickListener);
		mRecyclerView.setAdapter(mAdapter);
		mRecyclerView.addOnScrollListener(mOnScrollListener);;

		onRefresh();
		return view;
	}


	private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener(){
		private int lastVisibleItem;
		@Override
		public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
			super.onScrollStateChanged(recyclerView, newState);
			if (newState == RecyclerView.SCROLL_STATE_IDLE
					&& lastVisibleItem + 1 == mAdapter.getItemCount()
					&& mAdapter.isShowFooter()) {
				//加载更多
				LogUtils.d(TAG, "loading more data");
				mNewsPresenter.loadNews(mType, mPageIndex + Urls.PAZE_SIZE);
			}
		}

		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			super.onScrolled(recyclerView, dx, dy);
			lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
		}
	};


	private NewsAdapter.OnItemClickListener mOnItemClickListener = new NewsAdapter.OnItemClickListener() {
		@Override
		public void onItemClick(View view, int position) {
			NewsBean news = mAdapter.getItem(position);
			Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
			intent.putExtra("news", news);
			View transitionView = view.findViewById(R.id.ivNews);
			ActivityOptionsCompat options =
					ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
							transitionView, getString(R.string.transition_news_img));

			ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
		}
	};


	@Override
	public void onRefresh() {
		mPageIndex = 0 ;
		if (mData != null){

			mData.clear();
		}
		mNewsPresenter.loadNews(mType,mPageIndex);
	}

	@Override
	public void showProgress() {
		mSwipeRefreshWidget.setRefreshing(true);
	}

	@Override
	public void addNews(List<NewsBean> list) {

		mAdapter.isShowFooter(true);
		if(mData == null) {
			mData = new ArrayList<NewsBean>();
		}
		mData.addAll(list);
		if(mPageIndex == 0) {
			mAdapter.setmDate(mData);
		} else {
			//如果没有更多数据了,则隐藏footer布局
			if(list == null || list.size() == 0) {
				mAdapter.isShowFooter(false);
			}
			mAdapter.notifyDataSetChanged();
		}
		mPageIndex += Urls.PAZE_SIZE;
	}

	@Override
	public void hideProgress() {

		mSwipeRefreshWidget.setRefreshing(false);
	}

	@Override
	public void showLoadFailMsg() {

	}
}