package by.academy.service.input_validator.impl;

import by.academy.service.input_validator.CheckModify;

public class CheckModifyInput implements CheckModify {

    @Override
    public boolean isInputCorrect(String title, String brief, String content) {
        boolean isCorrect = title == null || "".equals(title) || title.length() > 150 || brief == null ||
                "".equals(brief) || brief.length() > 300 || content == null || "".equals(content) || content.length() > 2000;

        return !isCorrect;
    }
}
