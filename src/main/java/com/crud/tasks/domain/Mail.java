package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public class Mail {
    private final String mailTo;
    private final String subject;
    private final String templateName;
    private final Map<String, Object> context;
    private String toCc;

    public static class MailBuilder {
        private String mailTo;
        private String subject;
        private String templateName;
        private Map<String, Object> context = new HashMap<>();

        public MailBuilder mailTo(String mailTo) {
            this.mailTo = mailTo;
            return this;
        }

        public MailBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public MailBuilder templateName(String templateName) {
            this.templateName = templateName;
            return this;
        }

        public MailBuilder context(String name, Object value) {
            context.put(name, value);
            return this;
        }

        public Mail build() {
            return new Mail(mailTo, subject, templateName, context);
        }
    }

    private Mail(String mailTo, String subject, String templateName, Map<String, Object> context) {
        this.mailTo = mailTo;
        this.subject = subject;
        this.templateName = templateName;
        this.context = context;
    }
}