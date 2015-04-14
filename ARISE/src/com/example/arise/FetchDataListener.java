package com.example.arise;
 
import java.util.List;
 
public interface FetchDataListener {
    public void onFetchComplete(List<application> data);
    public void onFetchFailure(String msg);
}
