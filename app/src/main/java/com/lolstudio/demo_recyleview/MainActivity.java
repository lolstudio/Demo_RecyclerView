package com.lolstudio.demo_recyleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lolstudio.adapter.StudyAdapter;
import com.lolstudio.bean.Study;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvTest;
    private StudyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTest = (RecyclerView) findViewById(R.id.rv_test);
        rvTest.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StudyAdapter(this, getStudy());
        rvTest.setAdapter(adapter);
    }

    public List<Study> getStudy() {
        List<Study> value = new ArrayList<>();
        value.add(new Study("question1", new Study.Answer("111", new String[]{"answer1", "answer2", "answer3", "answer4"})));
        value.add(new Study("question2", new Study.Answer("222", new String[]{"answer1", "answer2", "answer3", "answer4", "answer5"})));
        value.add(new Study("question3", new Study.Answer("333", new String[]{"answer1", "answer2", "answer3", "answer4"})));
        value.add(new Study("question4", new Study.Answer("444", new String[]{"answer1", "answer2", "answer3", "answer4", "answer5"})));
        value.add(new Study("question5", new Study.Answer("555", new String[]{"answer1", "answer2", "answer3", "answer4"})));
        value.add(new Study("question6", new Study.Answer("666", new String[]{"answer1", "answer2", "answer3", "answer4", "answer5"})));
        value.add(new Study("question1", new Study.Answer("111", new String[]{"answer1", "answer2", "answer3", "answer4"})));
        value.add(new Study("question2", new Study.Answer("222", new String[]{"answer1", "answer2", "answer3", "answer4", "answer5"})));
        value.add(new Study("question3", new Study.Answer("333", new String[]{"answer1", "answer2", "answer3", "answer4"})));
        value.add(new Study("question4", new Study.Answer("444", new String[]{"answer1", "answer2", "answer3", "answer4", "answer5"})));
        value.add(new Study("question5", new Study.Answer("555", new String[]{"answer1", "answer2", "answer3", "answer4"})));
        value.add(new Study("question6", new Study.Answer("666", new String[]{"answer1", "answer2", "answer3", "answer4", "answer5"})));
        value.add(new Study("question1", new Study.Answer("111", new String[]{"answer1", "answer2", "answer3", "answer4"})));
        value.add(new Study("question2", new Study.Answer("222", new String[]{"answer1", "answer2", "answer3", "answer4", "answer5"})));
        value.add(new Study("question3", new Study.Answer("333", new String[]{"answer1", "answer2", "answer3", "answer4"})));
        value.add(new Study("question4", new Study.Answer("444", new String[]{"answer1", "answer2", "answer3", "answer4", "answer5"})));
        value.add(new Study("question5", new Study.Answer("555", new String[]{"answer1", "answer2", "answer3", "answer4"})));
        value.add(new Study("question6", new Study.Answer("666", new String[]{"answer1", "answer2", "answer3", "answer4", "answer5"})));
        value.add(new Study("question1", new Study.Answer("111", new String[]{"answer1", "answer2", "answer3", "answer4"})));
        value.add(new Study("question2", new Study.Answer("222", new String[]{"answer1", "answer2", "answer3", "answer4", "answer5"})));
        value.add(new Study("question3", new Study.Answer("333", new String[]{"answer1", "answer2", "answer3", "answer4"})));
        value.add(new Study("question4", new Study.Answer("444", new String[]{"answer1", "answer2", "answer3", "answer4", "answer5"})));
        value.add(new Study("question5", new Study.Answer("555", new String[]{"answer1", "answer2", "answer3", "answer4"})));
        value.add(new Study("question6", new Study.Answer("666", new String[]{"answer1", "answer2", "answer3", "answer4", "answer5"})));
        return value;
    }

    public void getValue(View v) {
//        adapter.getValue();//获得选定的值，不清楚服务器端数据格式
//        adapter.setErrorIndex(2,5);//设置答错的题目
    }




}
