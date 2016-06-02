package com.lolstudio.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.lolstudio.bean.Study;
import com.lolstudio.demo_recyclerview.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by andrew on 2016/5/31.
 */
public class StudyAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Study> list;
    private Context mContext;
    private LayoutInflater mInflater;
    /*
      使用map保存选择的key-value
     */
    private HashMap<Integer, Integer> states = new HashMap<>();
    /*
       提示view的position下标
     */
    private int tipPosition = -1;

    public StudyAdapter(Context con, List<Study> list) {
        this.list = list;
        this.mContext = con;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        findMateTipViewPosition();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getAnswers().getAnswerType();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        ViewHolder holder = null;
        switch (viewType) {
            case Study.CHOICE_XML:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_study_choiceitem, parent, false);
                holder = new ChoiceHolder(v);
                break;
            case Study.MATE_XML:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_study_mateitem, parent, false);
                holder = new MateHolder(v);
                break;
        }
        return holder;
    }

    /**
     * 获取错误的position
     * @param errorIndex
     */
    public void setErrorIndex(int... errorIndex) {
        for (int index : errorIndex) {
            list.get(index).setError(true);
        }
        notifyDataSetChanged();
    }


    public void getValue() {
        for (Map.Entry<Integer, Integer> entry : states.entrySet()) {
            System.out.println("key = " + list.get(entry.getKey()).getTitle() + ", value = " + entry.getValue());
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        long startTime = System.currentTimeMillis();

        Study study = list.get(position);
        switch (getItemViewType(position)) {
            case Study.CHOICE_XML:
                ChoiceHolder choiceHolder = (ChoiceHolder) holder;
                bindChoiceHolder(choiceHolder, study, position);
                showError(choiceHolder, study, Study.CHOICE_XML);
                break;
            case Study.MATE_XML:
                MateHolder mateHolder = (MateHolder) holder;
                bindMateHolder(mateHolder, study, position);
                showError(mateHolder, study, Study.MATE_XML);
                break;
        }


        long endTime = System.currentTimeMillis();
        System.out.println("time:" + (endTime - startTime));
    }

    /**
     * 设置答错题目的item底色
     * @param holder
     * @param study
     * @param type
     */
    public void showError(ViewHolder holder, Study study, int type) {
        if (study.isError()) {
            if (type == Study.CHOICE_XML) {
                ((ChoiceHolder) holder).titleView.setBackgroundColor(Color.RED);
            } else {
                ((MateHolder) holder).spinner.setBackgroundColor(Color.RED);
            }
        } else {
            if (type == Study.CHOICE_XML) {
                ((ChoiceHolder) holder).titleView.setBackgroundColor(0);
            } else {
                ((MateHolder) holder).spinner.setBackgroundColor(0);
            }
        }
    }

    private void bindMateHolder(MateHolder mateHolder, final Study study, int position) {

        if (tipPosition == position) {
            mateHolder.line2.setVisibility(View.VISIBLE);
        } else {
            mateHolder.line2.setVisibility(View.GONE);
        }
        int index = study.getAnswers().getSelectionIndex();
        mateHolder.spinner.setSelection(index);//保存选中的值

        mateHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                study.getAnswers().setSelectionIndex(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    private void bindChoiceHolder(ChoiceHolder choiceHolder, Study study, final int position) {

        choiceHolder.titleView.setText(study.getTitle());

        if (position == 0) {
            choiceHolder.line1.setVisibility(View.VISIBLE);
        } else {
            choiceHolder.line1.setVisibility(View.GONE);
        }


        int size = study.getAnswers().getValue().length;
        final RadioGroup radioGroup = new RadioGroup(mContext);
        for (int i = 0; i < size; i++) {
            choiceHolder.ll_content.removeAllViews();
            View childView = mInflater.inflate(R.layout.adapter_addview_answer, null);
            final RadioButton radioButton = (RadioButton) childView.findViewById(R.id.radioButton);
            radioButton.setId(i);
            radioButton.setText(study.getAnswers().getValue()[i]);
            radioGroup.addView(childView);
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    states.put(position, v.getId());
                }
            });
            if (states.get(position) != null && radioButton.getId() == states.get(position)) {
                radioButton.setChecked(true);
            }
        }
        choiceHolder.ll_content.addView(radioGroup);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    class ChoiceHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        LinearLayout ll_content;
        View line1;

        public ChoiceHolder(View itemView) {
            super(itemView);
            line1 = itemView.findViewById(R.id.line1);
            titleView = (TextView) itemView.findViewById(R.id.tv_title);
            ll_content = (LinearLayout) itemView.findViewById(R.id.ll_content);
        }

    }

    class MateHolder extends RecyclerView.ViewHolder {
        Spinner spinner;
        View line2;

        public MateHolder(View itemView) {
            super(itemView);
            spinner = (Spinner) itemView.findViewById(R.id.spinner);
            line2 = itemView.findViewById(R.id.line2);
        }
    }

    /**
     * 设置getAnswerType为1的第一次出现的position，使view显示
     */
    public void findMateTipViewPosition() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAnswers().getAnswerType() == Study.MATE_XML) {
                tipPosition = i;
                break;
            }
        }
    }

}
