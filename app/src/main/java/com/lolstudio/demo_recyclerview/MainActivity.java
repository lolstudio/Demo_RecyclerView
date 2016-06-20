package com.lolstudio.demo_recyclerview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lolstudio.adapter.StudyAdapter;
import com.lolstudio.bean.Study;
import com.lolstudio.widget.SmoothScrollLinearLayoutManager;
import com.lolstudio.widget.WrapRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WrapRecyclerView rvTest;
    private StudyAdapter adapter;
    private SmoothScrollLinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTest = (WrapRecyclerView) findViewById(R.id.rv_test);
        layoutManager=new SmoothScrollLinearLayoutManager(this);
        rvTest.setLayoutManager(layoutManager);
        adapter = new StudyAdapter(this, getStudy());




        RelativeLayout sendBottomRL=new RelativeLayout(this);
        sendBottomRL.setBackgroundColor(Color.parseColor("#EEEEEE"));
        sendBottomRL.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,200));
        Button sendBtn=new Button(this);
        sendBtn.setText("complete");
        sendBtn.setTextColor(Color.WHITE);
        sendBtn.setBackgroundResource(R.drawable.session_question_send);
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getValue();
                adapter.setErrorIndex(2,5,8);//设置答错的题目
                rvTest.smoothScrollToPosition(5);//滚动动画
            }
        });
        sendBottomRL.addView(sendBtn,params);


        rvTest.addFootView(sendBottomRL);
        rvTest.setAdapter(adapter);

    }

    public List<Study> getStudy() {
        List<Study> value = new ArrayList<>();
        value.add(new Study("question1", new Study.Answer(1,0, "a111", new String[]{"answer1", "answer2", "answer3", "answer4"})));
        value.add(new Study("question2", new Study.Answer(2,0, "a222", new String[]{"answer1", "answer2", "answer3", "answer4", "answer5"})));
        value.add(new Study("question3", new Study.Answer(3,0, "a333", new String[]{"answer1", "answer2", "answer3", "answer4"})));
        value.add(new Study("question1", new Study.Answer(1,0, "a111", new String[]{"answer1", "answer2", "answer3", "answer4"})));
        value.add(new Study("question2", new Study.Answer(2,0, "a222", new String[]{"answer1", "answer2", "answer3", "answer4", "answer5"})));
        value.add(new Study("question3", new Study.Answer(3,0, "a333", new String[]{"answer1", "answer2", "answer3", "answer4"})));
        value.add(new Study("question3", new Study.Answer(4,1, "b111", new String[]{"answer1", "answer2", "answer3", "answer4"})));
        value.add(new Study("question4", new Study.Answer(5,1, "b222", new String[]{"answer1", "answer2", "answer3", "answer4", "answer5"})));
        value.add(new Study("question5", new Study.Answer(6,1, "b333", new String[]{"answer1", "answer2", "answer3", "answer4"})));
//        value.add(new Study("question3", new Study.Answer(1, "b333", new String[]{"answer1", "answer2", "answer3", "answer4"})));
//        value.add(new Study("question4", new Study.Answer(1, "b444", new String[]{"answer1", "answer2", "answer3", "answer4", "answer5"})));
//        value.add(new Study("question5", new Study.Answer(1, "b555", new String[]{"answer1", "answer2", "answer3", "answer4"})));
//        value.add(new Study("question6", new Study.Answer(1, "b666", new String[]{"answer1", "answer2", "answer3", "answer4", "answer5"})));
        return value;
    }


}
