package org.springframework.batch.admin.sample.lead.support;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.eval.ServerEvaluationCall;

/**
 * @author Dave Syer
 *
 */
public class StagingWriter implements ItemWriter<String> {
	
	private DatabaseClient client;

	public void setDatabaseClient(DatabaseClient client) {
		this.client = client;
	}

	public void write(List<? extends String> values) throws Exception {
		ServerEvaluationCall theCall = client.newServerEval();
		theCall.xquery("xdmp:document-insert('staging.xml', <hello />)");
	}
}
