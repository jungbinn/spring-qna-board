package com.line2019.qna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QnaApplication {

	public static void main(String[] args) {
		System.setProperty("spirng.devtools.restart.enabled", "false");
		System.setProperty("spirng.devtools.livereload.enabled", "true");
		SpringApplication.run(QnaApplication.class, args);
	}

}

