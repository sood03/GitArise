package com.example.arise;

import java.util.List;

public interface catFetchDataListener {
	public void onFetchComplete(List<categoryapplication> data);
    public void onFetchFailure(String msg);

}
