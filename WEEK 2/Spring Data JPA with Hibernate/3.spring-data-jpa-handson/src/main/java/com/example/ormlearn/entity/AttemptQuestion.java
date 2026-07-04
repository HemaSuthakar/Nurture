package com.example.ormlearn.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "attempt_question")
public class AttemptQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_id")
    private Attempt attempt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(mappedBy = "attemptQuestion", fetch = FetchType.LAZY)
    private List<AttemptOption> attemptOptions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Attempt getAttempt() {
        return attempt;
    }

    public void setAttempt(Attempt attempt) {
        this.attempt = attempt;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<AttemptOption> getAttemptOptions() {
        return attemptOptions;
    }

    public void setAttemptOptions(List<AttemptOption> attemptOptions) {
        this.attemptOptions = attemptOptions;
    }
}
