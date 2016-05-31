package com.lolstudio.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lolstudio.bean.Study;
import com.lolstudio.demo_recyleview.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by andrew on 2016/5/31.
 */
public class StudyAdapter extends RecyclerView.Adapter<StudyAdapter.ViewHolder> {
    private List<Study> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private HashMap<Integer, Integer> states = new HashMap<>();

    public StudyAdapter(Context con, List<Study> list) {
        this.list = list;
        this.mContext = con;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_study_item, parent, false);
        return new ViewHolder(v);
    }

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
        holder.titleView.setText(study.getTitle());

        if (study.isError()) {
            holder.titleView.setBackgroundColor(Color.RED);
        } else {
            holder.titleView.setBackgroundColor(0);
        }
        int size = study.getAnswers().getValue().length;
        final RadioGroup radioGroup = new RadioGroup(mContext);
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < size; i++) {
            holder.ll_content.removeAllViews();
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
        holder.ll_content.addView(radioGroup);

        long endTime = System.currentTimeMillis();
        System.out.println("time:" + (endTime - startTime));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        LinearLayout ll_content;

        public ViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.tv_title);
            ll_content = (LinearLayout) itemView.findViewById(R.id.ll_content);
        }

    }

}
