package com.londonappbrewery.destini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
    TextView mStoryTextView;
    Button mAnswerTop;
    Button mAnswerBottom;

    private Answer mT1_Ans1 = new Answer(R.string.T1_Ans1);
    private Answer mT1_Ans2 = new Answer(R.string.T1_Ans2);
    private Answer mT2_Ans1 = new Answer(R.string.T2_Ans1);
    private Answer mT2_Ans2 = new Answer(R.string.T2_Ans2);
    private Answer mT3_Ans1 = new Answer(R.string.T3_Ans1);
    private Answer mT3_Ans2 = new Answer(R.string.T3_Ans2);

    private Story mT1_Story = new Story(R.string.T1_Story);
    private Story mT2_Story = new Story(R.string.T2_Story);
    private Story mT3_Story = new Story(R.string.T3_Story);
    private Story mT4_Story = new Story(R.string.T4_End);
    private Story mT5_Story = new Story(R.string.T5_End);
    private Story mT6_Story = new Story(R.string.T6_End);

    private Story mStorySelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mapping
        mT1_Story.setAnswerTop(mT1_Ans1);
        mT1_Story.setAnswerBottom(mT1_Ans2);
            mT1_Ans1.setChildStory(mT3_Story);
            mT1_Ans2.setChildStory(mT2_Story);
                mT3_Story.setAnswerTop(mT3_Ans1);
                mT3_Story.setAnswerBottom(mT3_Ans2);
                    mT3_Ans1.setChildStory(mT6_Story);
                    mT3_Ans2.setChildStory(mT5_Story);
                mT2_Story.setAnswerTop(mT2_Ans1);
                mT2_Story.setAnswerBottom(mT2_Ans2);
                    mT2_Ans1.setChildStory(mT3_Story);
                    mT2_Ans2.setChildStory(mT4_Story);

        mStorySelected = mT1_Story;

        mStoryTextView = (TextView) findViewById(R.id.storyTextView);
        mAnswerTop = (Button) findViewById(R.id.buttonTop);
        mAnswerBottom = (Button) findViewById(R.id.buttonBottom);

        mAnswerTop.setText(mStorySelected.getAnswerTop().getAnswerID());
        mAnswerBottom.setText(mStorySelected.getAnswerBottom().getAnswerID());

        mAnswerTop.setOnClickListener(new View.OnClickListener() { //anonymous
            @Override
            public void onClick(View view) {
                mStorySelected = mStorySelected.getAnswerTop().getChildStory();
                mStoryTextView.setText(mStorySelected.getStoryID());
                if(mStorySelected.getAnswerTop() == null){
                    mAnswerTop.setVisibility(View.GONE);
                    mAnswerBottom.setVisibility(View.GONE);
                }else{
                    mAnswerTop.setText(mStorySelected.getAnswerTop().getAnswerID());
                    mAnswerBottom.setText(mStorySelected.getAnswerBottom().getAnswerID());

                }
            }
        });

        mAnswerBottom.setOnClickListener(new View.OnClickListener() { //anonymous
            @Override
            public void onClick(View view) {
                mStorySelected = mStorySelected.getAnswerBottom().getChildStory();
                mStoryTextView.setText(mStorySelected.getStoryID());
                if(mStorySelected.getAnswerTop()==null){
                    mAnswerTop.setVisibility(View.GONE);
                    mAnswerBottom.setVisibility(View.GONE);
                }else{
                    mAnswerTop.setText(mStorySelected.getAnswerTop().getAnswerID());
                    mAnswerBottom.setText(mStorySelected.getAnswerBottom().getAnswerID());

                }
            }
        });


        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:



        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:




        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mStorySelected = mT1_Story;
    }
}
