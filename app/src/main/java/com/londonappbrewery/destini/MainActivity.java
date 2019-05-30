package com.londonappbrewery.destini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.londonappbrewery.destini.models.Answer;
import com.londonappbrewery.destini.models.Story;

public class MainActivity extends AppCompatActivity {


    TextView mStoryTextView;
    Button mAnswerTop;
    Button mAnswerBottom;

    // TODO: Declare as variaveis aqui:

    Story mT1 = new Story(R.string.T1_Story);
    Answer mA1 = new Answer(R.string.T1_Ans1);
    Answer mA2 = new Answer(R.string.T1_Ans2);

    Story mT2 = new Story(R.string.T2_Story);
    Answer mB1 = new Answer(R.string.T2_Ans1);
    Answer mB2 = new Answer(R.string.T2_Ans2);

    Story mT3 = new Story(R.string.T3_Story);
    Answer mC1 = new Answer(R.string.T3_Ans1);
    Answer mC2 = new Answer(R.string.T3_Ans2);

    Story mT4 = new Story(R.string.T4_End);
    Story mT5 = new Story(R.string.T5_End);
    Story mT6 = new Story(R.string.T6_End);

    //indice corrente da historia
    private Story mStorySelected;
    private int mStoryIndex;


    public void direct(Story storySelect) {
        mStoryTextView.setText(storySelect.getStoryID());
        if (storySelect.getAnswerTop() != null) {
            mAnswerTop.setText(storySelect.getAnswerTop().getAnswerID());
            mAnswerBottom.setText(storySelect.getAnswerBottom().getAnswerID());


        } else{
            mAnswerTop.setVisibility(View.INVISIBLE);
            mAnswerBottom.setVisibility(View.INVISIBLE);
        }
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            mStorySelected = (Story) savedInstanceState.getSerializable("StoryKey");
            direct(mStorySelected);


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null) {
            mStoryIndex = savedInstanceState.getInt("StoryKey");
        }

        //1° TODO: Faça o link do layout com a activity

        mStoryTextView = findViewById(R.id.storyTextView);
        mAnswerTop = findViewById(R.id.buttonTop);
        mAnswerBottom = findViewById(R.id.buttonBottom);

        //TODO:faça o mapeamento da história=
        mT1.setAnswerTop(mA1);
        mT1.setAnswerBottom(mA2);
        mA1.setChildStory(mT3);
        mA2.setChildStory(mT2);
        mT2.setAnswerTop(mB1);
        mT2.setAnswerBottom(mB2);
        mB1.setChildStory(mT3);
        mB2.setChildStory(mT4);
        mT3.setAnswerTop(mC1);
        mT3.setAnswerBottom(mC2);
        mC1.setChildStory(mT6);
        mC2.setChildStory(mT5);



        mStorySelected = mT1;
        mStoryTextView.setText(mStorySelected.getStoryID());
        mAnswerTop.setText(mStorySelected.getAnswerTop().getAnswerID());
        mAnswerBottom.setText(mStorySelected.getAnswerBottom().getAnswerID());

        mAnswerTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStorySelected = mStorySelected.getAnswerTop().getChildStory();
                mStoryTextView.setText(mStorySelected.getStoryID());
                if (mStorySelected.getAnswerTop()==null){
                    mAnswerTop.setVisibility(View.GONE);
                    mAnswerBottom.setVisibility(View.GONE);
                } else {
                    mAnswerTop.setText(mStorySelected.getAnswerTop().getAnswerID());
                    mAnswerBottom.setText(mStorySelected.getAnswerBottom().getAnswerID());
                }
            }
        });

        mAnswerBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStorySelected = mStorySelected.getAnswerBottom().getChildStory();
                mStoryTextView.setText(mStorySelected.getStoryID());
                if (mStorySelected.getAnswerBottom()==null){
                    mAnswerTop.setVisibility(View.GONE);
                    mAnswerBottom.setVisibility(View.GONE);
                } else {
                    mAnswerTop.setText(mStorySelected.getAnswerTop().getAnswerID());
                    mAnswerBottom.setText(mStorySelected.getAnswerBottom().getAnswerID());
                }
            }
        });
        // TODO: Coloque o evento do click do botão, caso precise colocar a visibilidade no botão invisivel utilize a função
        // do botão setVisibility(View.GONE):


    }
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("StoryKey", mStoryIndex);



    }
}