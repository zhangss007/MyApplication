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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

import java.util.List;

import demo.myapplication.bean.NewsBean;
import demo.myapplication.news.presenter.NewsPresenter;
import demo.myapplication.news.presenter.NewsPresenterImpl;
import demo.myapplication.news.view.NewView;

public class NewsListFragment extends Fragment implements NewView{

	private static final String FRAGMENT_TYPE = "type";
	public static final int NEWS_TYPE_TOP = 0;
	public static final int NEWS_TYPE_NBA = 1;
	public static final int NEWS_TYPE_CARS = 2;
	public static final int NEWS_TYPE_JOKES = 3;

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

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		FrameLayout fl = new FrameLayout(getActivity());
		fl.setLayoutParams(params);

		final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources()
				.getDisplayMetrics());

		TextView v = new TextView(getActivity());
		params.setMargins(margin, margin, margin, margin);
		v.setLayoutParams(params);
		v.setLayoutParams(params);
		v.setGravity(Gravity.CENTER);
		v.setBackgroundResource(R.drawable.background_card);
		v.setText("CARD " + (mType + 1));
		fl.addView(v);

		onRefresh();

		return fl;
	}


	private void onRefresh(){

		mNewsPresenter.loadNews(mType,mPageIndex);
	}

	@Override
	public void showProgress() {

	}

	@Override
	public void addNews(List<NewsBean> list) {

	}

	@Override
	public void hideProgress() {

	}

	@Override
	public void showLoadFailMsg() {

	}
}