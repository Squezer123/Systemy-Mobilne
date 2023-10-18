package pl.edu.pb.wi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;
    private int correctAnswers = 0;

    private  int currentIndex = 0;
    private Question[] questions = new Question[]{
            new Question(R.string.q_japan,false),
            new Question(R.string.q_water,true),
            new Question(R.string.q_wenus,true),
            new Question(R.string.q_light, true),
            new Question(R.string.q_moon,false)
    };

    private void checkAnswerCorrectenss(boolean userAnswer) {
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if (userAnswer == correctAnswer) {
            resultMessageId = R.string.correct_answer;
            correctAnswers++;
        } else {
            resultMessageId = R.string.wrong_answer;
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }


    private void setNextQuestion() {
        if (currentIndex < questions.length) {
            questionTextView.setText(getString(questions[currentIndex].getQuestionId()));
        } else {
            String resultMessage = getString(R.string.result_message, correctAnswers, questions.length);
            Toast.makeText(this, resultMessage, Toast.LENGTH_SHORT).show();
            trueButton.setEnabled(false);
            falseButton.setEnabled(false);
            nextButton.setEnabled(false);

            String finalResultMessage = getString(R.string.final_result_message, correctAnswers, questions.length);
            Toast.makeText(this, finalResultMessage, Toast.LENGTH_LONG).show();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.fasle_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);

        questionTextView.setText(getString(questions[currentIndex].getQuestionId()));
        trueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v) {
                checkAnswerCorrectenss(true);
            }

        });

        falseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v) {
                checkAnswerCorrectenss(false);
            }

        });

        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1);
                setNextQuestion();
            }

        });
    }
}