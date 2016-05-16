/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.task.app.timestamp;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A commandline runner that prints a timestamp.
 */
@EnableTask
@Configuration
@EnableConfigurationProperties({ TimestampTaskProperties.class })
public class TimestampTaskConfiguration {

    @Bean
    public TimestampTask timeStampTask() {
        return new TimestampTask();
    }

    /**
     * A commandline runner that prints a timestamp.
     */
    public class TimestampTask implements CommandLineRunner {
        private final org.slf4j.Logger logger = LoggerFactory.getLogger(TimestampTask.class);

        @Autowired
        private TimestampTaskProperties config;

        @Override
        public void run(String... strings) throws Exception {
            DateFormat dateFormat = new SimpleDateFormat(config.getFormat());
            logger.info(dateFormat.format(new Date()));
        }
    }

}
