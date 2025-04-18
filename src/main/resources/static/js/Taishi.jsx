// src/Quiz.js
import React, { useState } from 'react';
import './Quiz.css'; // Nhập file CSS nếu bạn tạo một file riêng

const Quiz = () => {
    const [selectedOption, setSelectedOption] = useState(null);
    const [isSubmitted, setIsSubmitted] = useState(false);

    const question = "What is the capital of France?";
    const options = [
        { id: 1, text: "Berlin", isCorrect: false },
        { id: 2, text: "Madrid", isCorrect: false },
        { id: 3, text: "Paris", isCorrect: true },
        { id: 4, text: "Rome", isCorrect: false }
    ];

    const handleOptionClick = (option) => {
        setSelectedOption(option);
        setIsSubmitted(true);
    };

    return (
        <div>
            <h1>Kahoot-style Quiz</h1>
            <div className="question">{question}</div>
            <div className="answers-grid">
                {options.map((option, index) => (
                    <div
                        key={option.id}
                        className={`answer-option option-${index + 1} ${isSubmitted ? (option.isCorrect ? 'correct' : (selectedOption && selectedOption.id === option.id ? 'incorrect' : '')) : ''}`}
                        onClick={() => !isSubmitted && handleOptionClick(option)}
                    >
                        {option.text}
                    </div>
                ))}
            </div>
            {isSubmitted && (
                <div className="message">
                    {selectedOption.isCorrect ? "Correct Answer!" : "Incorrect Answer!"}
                </div>
            )}
        </div>
    );
};

export default Quiz;
