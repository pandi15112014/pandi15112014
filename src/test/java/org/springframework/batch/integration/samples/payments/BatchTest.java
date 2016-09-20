/*
 * Copyright 2002-2014 the original author or authors.
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
package org.springframework.batch.integration.samples.payments;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.integration.samples.payments.config.CommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Marius Bogoevici
 * @author Gunnar Hillert
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CommonConfig.class)
public class BatchTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired @Qualifier("statuses")
	private QueueChannel statusesChannel;

	@Autowired
	private JobRepository jobRepository;

	@Test
	@SuppressWarnings("unchecked")
	public void runBatch() throws Exception {
		//120 s should provide enough time for the poller to detect the file and process it
		JobExecution jobExecution = ((Message<JobExecution>) statusesChannel.receive(120000)).getPayload();
		ExitStatus exitStatus = jobExecution.getExitStatus();
		Assert.assertEquals(ExitStatus.COMPLETED, exitStatus);
		int count = jdbcTemplate.queryForObject("select count(*) from payments", Integer.class);
		Assert.assertEquals(27, count);
	}
}
