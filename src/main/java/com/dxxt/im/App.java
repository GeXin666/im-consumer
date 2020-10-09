package com.dxxt.im;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		//以非web方式启动
        new SpringApplicationBuilder(App.class).web(WebApplicationType.NONE).run(args);
	}

}

