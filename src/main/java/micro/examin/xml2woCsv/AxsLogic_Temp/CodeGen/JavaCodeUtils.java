package micro.examin.xml2woCsv.AxsLogic_Temp.CodeGen;

public class JavaCodeUtils {
	public static String config = "package com.axslogic.es.esservice.jobs.config;\n" +
			"\n" +
			"import com.axslogic.es.esservice.config.ESTransportClient;\n" +
			"import com.axslogic.es.esservice.dto./*$PRODUCT+Typefull*/;\n" +
			"import com.axslogic.es.esservice.jobs.JobCompletionNotificationListener;\n" +
			"import com.axslogic.es.esservice.jobs.processor./*$PRODUCT+Typefull*/DocProcessor;\n" +
			"import com.axslogic.es.esservice.jobs.writer./*$PRODUCT+Typefull*/Writer;\n" +
			"import org.springframework.batch.core.Job;\n" +
			"import org.springframework.batch.core.Step;\n" +
			"import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;\n" +
			"import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;\n" +
			"import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;\n" +
			"import org.springframework.batch.core.configuration.annotation.StepScope;\n" +
			"import org.springframework.batch.core.launch.support.RunIdIncrementer;\n" +
			"import org.springframework.batch.item.file.FlatFileItemReader;\n" +
			"import org.springframework.batch.item.file.LineMapper;\n" +
			"import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;\n" +
			"import org.springframework.batch.item.file.mapping.DefaultLineMapper;\n" +
			"import org.springframework.batch.item.file.mapping.FieldSetMapper;\n" +
			"import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;\n" +
			"import org.springframework.batch.item.file.transform.LineTokenizer;\n" +
			"import org.springframework.beans.factory.annotation.Autowired;\n" +
			"import org.springframework.beans.factory.annotation.Value;\n" +
			"import org.springframework.context.annotation.Bean;\n" +
			"import org.springframework.context.annotation.Configuration;\n" +
			"import org.springframework.core.io.FileSystemResource;\n" +
			"\n" +
			"/**\n" +
			" * @author examin\n" +
			" * <p>\n" +
			" * The class is a part of Portfolio batch job and is referenced by the\n" +
			" * job processor to process the records.\n" +
			" */\n" +
			"@EnableBatchProcessing\n" +
			"@Configuration\n" +
			"\n" +
			"public class /*$PRODUCT+Typefull*/Config {\n" +
			"\tprivate static final String OVERRIDDEN_BY_EXPRESSION = null;\n" +
			"\tprivate final int FETCH_SIZE = 10000;\n" +
			"\tprivate StepBuilderFactory stepBuilderFactory;\n" +
			"\tprivate JobBuilderFactory jobBuilderFactory;\n" +
			"\tprivate ESTransportClient esTransportClient;\n" +
			"\n" +
			"\t/*@Value(\"#{jobParameters['filepath']}\")\n" +
			"\tprivate Resource[] resources;*/\n" +
			"\n"+
			"\t@Autowired\n" +
			"\tpublic /*$PRODUCT+Typefull*/Config(StepBuilderFactory stepBuilderFactory,\n" +
			"\t\t\t\t\t\t JobBuilderFactory jobBuilderFactory, ESTransportClient esTransportClient) {\n" +
			"\t\tthis.stepBuilderFactory = stepBuilderFactory;\n" +
			"\t\tthis.jobBuilderFactory = jobBuilderFactory;\n" +
			"\t\tthis.esTransportClient = esTransportClient;\n" +
			"\t}\n" +
			"//\n" +
			"//\t@Bean\n" +
			"//\tpublic MultiResourceItemReader</*$PRODUCT+Typefull*/> multiResourceItemReader() {\n" +
			"//\t\tMultiResourceItemReader</*$PRODUCT+Typefull*/> resourceItemReader = new MultiResourceItemReader</*$PRODUCT+Typefull*/>();\n" +
			"//\t\tresourceItemReader.setResources(resources);\n" +
			"//\t\tresourceItemReader.setDelegate(reader());\n" +
			"//\t\treturn resourceItemReader;\n" +
			"//\t}\n" +
			"\n" +
			"\t@Bean\n" +
			"\t@StepScope\n" +
			"\tpublic FlatFileItemReader</*$PRODUCT+Typefull*/> /*$product+Typefull*/Reader(@Value(\"#{jobParameters[filepath]}\") String filepath) {\n" +
			"\t\tFlatFileItemReader</*$PRODUCT+Typefull*/> reader = new FlatFileItemReader</*$PRODUCT+Typefull*/>();\n" +
			"\t\treader.setResource(new FileSystemResource(filepath));\n" +
			"\t\t// reader.setResource(new ClassPathResource(\"20170531.csv\")); \n" +
			"\t\t// reader.setLinesToSkip(1);\n" +
			"\t\treader.setLineMapper(/*$product+Typefull*/CreateLineMapper());\n" +
			"\t\treturn reader;\n" +
			"\t}\n" +
			"\n" +
			"\tprivate LineMapper</*$PRODUCT+Typefull*/> /*$product+Typefull*/CreateLineMapper() {\n" +
			"\t\tDefaultLineMapper</*$PRODUCT+Typefull*/> lineMapper = new DefaultLineMapper<>();\n" +
			"\n" +
			"\t\tLineTokenizer lineTokenizer = /*$product+Typefull*/CreatelineTokenizer();\n" +
			"\t\tlineMapper.setLineTokenizer(lineTokenizer);\n" +
			"\n" +
			"\t\tFieldSetMapper</*$PRODUCT+Typefull*/> informationMapper = /*$product+Typefull*/CreateInformationMapper();\n" +
			"\t\tlineMapper.setFieldSetMapper(informationMapper);\n" +
			"\n" +
			"\t\treturn lineMapper;\n" +
			"\t}\n" +
			"\n" +
			"\tprivate LineTokenizer /*$product+Typefull*/CreatelineTokenizer() {\n" +
			"\t\tDelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();\n" +
			"\t\tlineTokenizer.setDelimiter(\"|\");\n" +
			"\t\tlineTokenizer.setNames(\n" +
			"\n" +
			"\t\t/*$column names*/);\n" +
			"\t\treturn lineTokenizer;\n" +
			"\t}\n" +
			"\n" +
			"\tprivate FieldSetMapper</*$PRODUCT+Typefull*/> /*$product+Typefull*/CreateInformationMapper() {\n" +
			"\t\tBeanWrapperFieldSetMapper</*$PRODUCT+Typefull*/> informationMapper = new BeanWrapperFieldSetMapper<>();\n" +
			"\t\tinformationMapper.setTargetType(/*$PRODUCT+Typefull*/.class);\n" +
			"\t\treturn informationMapper;\n" +
			"\t}\n" +
			"\n" +
			"\t@Bean\n" +
			"\tpublic /*$PRODUCT+Typefull*/DocProcessor /*$product+Typefull*/Processor() {\n" +
			"\t\treturn new /*$PRODUCT+Typefull*/DocProcessor();\n" +
			"\t}\n" +
			"\n" +
			"\t@Bean\n" +
			"\t@StepScope\n" +
			"\tpublic /*$PRODUCT+Typefull*/Writer /*$product+Typefull*/Writer(@Value(\"#{jobParameters[subscriber]}\") String subscriber) {\n" +
			"\t\t/*$PRODUCT+Typefull*/Writer /*product*/Writer = new /*$PRODUCT+Typefull*/Writer();\n" +
			"\t\t/*product*/Writer.setWriterConfig(subscriber, \"/*product*/\", \"/*typefull*/\", this.esTransportClient);\n" +
			"\t\treturn /*product*/Writer;\n" +
			"\t}\n" +
			"\n" +
			"\t@Bean(name = \"/*$PRODUCT+Typefull*/\")\n" +
			"\tpublic Job import/*$PRODUCT+Typefull*/DataJob(JobCompletionNotificationListener listener, Step /*$product+Typefull*/Step) {\n" +
			"\t\treturn jobBuilderFactory.get(\"import/*$PRODUCT+Typefull*/DataJob\")\n" +
			"\t\t\t\t.incrementer(new RunIdIncrementer())\n" +
			"\t\t\t\t.listener(listener)\n" +
			"\t\t\t\t.flow(/*$product+Typefull*/Step)\n" +
			"\t\t\t\t.end().build();\n" +
			"\t}\n" +
			"\n" +
			"\t@Bean\n" +
			"\tpublic Step /*$product+Typefull*/Step() {\n" +
			"\t\treturn stepBuilderFactory.get(\"step\").</*$PRODUCT+Typefull*/, /*$PRODUCT+Typefull*/>chunk(this.FETCH_SIZE)\n" +
			"\t\t\t\t.reader(/*$product+Typefull*/Reader(OVERRIDDEN_BY_EXPRESSION))\n" +
			"\t\t\t\t.processor(/*$product+Typefull*/Processor())\n" +
			"\t\t\t\t.writer(/*$product+Typefull*/Writer(OVERRIDDEN_BY_EXPRESSION))\n" +
			"\t\t\t\t.build();\n" +
			"\n" +
			"\t}\n" +
			"}";


	public static String processor = "package com.axslogic.es.esservice.jobs.processor;\n" +
			"\n" +
			"import com.axslogic.es.esservice.dto./*$PRODUCT+Typefull*/;\n" +
			"import org.springframework.batch.item.ItemProcessor;\n" +
			"import org.springframework.stereotype.Component;\n" +
			"\n" +
			"/**\n" +
			" * @author examin\n" +
			" * <p>\n" +
			" * The class is a part of Portfolio batch job and is referenced by the\n" +
			" * job processor to process the records.\n" +
			" */\n" +
			"@Component\n" +
			"public class /*$PRODUCT+Typefull*/DocProcessor implements ItemProcessor</*$PRODUCT+Typefull*/, /*$PRODUCT+Typefull*/> {\n" +
			"\n" +
			"\t/*\n" +
			"\t * (non-Javadoc)\n" +
			"\t *\n" +
			"\t * @see org.springframework.batch.item.ItemProcessor#process(java.lang.Object)\n" +
			"\t */\n" +
			"\t@Override\n" +
			"\tpublic /*$PRODUCT+Typefull*/ process(final /*$PRODUCT+Typefull*/ item) throws Exception {\n" +
			"\t\treturn item;\n" +
			"\t}\n" +
			"}";
	public static String dto = "package com.axslogic.es.esservice.dto;\n" +
			"\n" +
			"import org.springframework.data.annotation.Id;\n" +
			"\n" +
			"import java.util.UUID;\n" +
			"\n" +
			"public class /*$PRODUCT+Typefull*/ {\n" +
			"\t@Id\n" +
			"\tprivate String id;\n" +
			"\n" +
			"/*$dataString*/\n" +
			"\n" +
			"\tpublic /*$PRODUCT+Typefull*/(){\n" +
			"\t\tlong timeStamp = System.currentTimeMillis();\n" +
			"\t\tthis.id = UUID.randomUUID().toString() + timeStamp;\n" +
			"\t}\n" +
			"\n" +
			"\tpublic void setid(String id) {\n" +
			"\t\tthis.id = Utils.getValidString(id);\n" +
			"\t}\n" +
			"\n" +
			"\tpublic String getId() {\n" +
			"\t\treturn id;\n" +
			"\t}\n" +
			"\n" +
			"\n" +
			"\t/*$getterSetterString*/\n" +
			"\n" +
			"}";

	public static String writer = "package com.axslogic.es.esservice.jobs.writer;" +
			"\n" +
			"import com.axslogic.es.esservice.config.ESTransportClient;\n" +
			"import com.axslogic.es.esservice.dto./*$PRODUCT+Typefull*/;\n" +
			"import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;\n" +
			"import org.elasticsearch.action.bulk.BulkRequestBuilder;\n" +
			"import org.elasticsearch.action.bulk.BulkResponse;\n" +
			"import org.elasticsearch.action.support.master.AcknowledgedResponse;\n" +
			"import org.elasticsearch.client.Client;\n" +
			"import org.elasticsearch.common.settings.Settings;\n" +
			"import org.elasticsearch.common.xcontent.XContentFactory;\n" +
			"import org.elasticsearch.common.xcontent.XContentType;\n" +
			"import org.json.JSONException;\n" +
			"import org.json.JSONObject;\n" +
			"import org.slf4j.Logger;\n" +
			"import org.springframework.batch.item.ItemWriter;\n" +
			"import org.springframework.beans.factory.InitializingBean;\n" +
			"import org.springframework.beans.factory.annotation.Autowired;\n" +
			"import org.springframework.stereotype.Component;\n" +
			"\n" +
			"import java.util.List;\n" +
			"\n" +
			"/**\n" +
			" * @author examin\n" +
			" * <p>\n" +
			" * The class is a part of Portfolio batch job and is referenced by the\n" +
			" * job processor to write the read data to ES.\n" +
			" */\n" +
			"@Component\n" +
			"public class /*$PRODUCT+Typefull*/Writer implements ItemWriter</*$PRODUCT+Typefull*/>, InitializingBean {\n" +
			"\n" +
			"\tprivate static final Logger log = org.slf4j.LoggerFactory.getLogger(/*$PRODUCT+Typefull*/Writer.class);\n" +
			"\t@Autowired\n" +
			"\tESTransportClient esTransportClient;\n" +
			"\tprivate String indexName;\n" +
			"\tprivate Client mClient;\n" +
			"\tprivate String numOfShards;\n" +
			"\tprivate BulkRequestBuilder bulkRequest;\n" +
			"\n" +
			"\t/**\n" +
			"\t * @param subscriber\n" +
			"\t */\n" +
			"\tpublic void setWriterConfig(String subscriber, String product, String productType, ESTransportClient esTransportClient) {\n" +
			"\t\tthis.esTransportClient = esTransportClient;\n" +
			"\t\tthis.mClient = this.esTransportClient.getTransportClient(subscriber);\n" +
			"\t\tthis.bulkRequest = mClient.prepareBulk();\n" +
			"\t\tthis.indexName = subscriber + \"_\" + product + \"_\" + productType;\n" +
			"\t\tthis.indexName = this.indexName.toLowerCase();\n" +
			"\t\tnumOfShards = this.esTransportClient.getSubscriberProductShards(subscriber,product);\n" +
			"\t\tputIndexMapping();\n" +
			"\t}\n" +
			"\n" +
			"\t/**\n" +
			"\t * The method is responsible to update the mapping for the ES index,\n" +
			"\t * and is being referenced by the constructor.\n" +
			"\t *\n" +
			"\t * @return\n" +
			"\t */\n" +
			"\tprivate boolean putIndexMapping() {\n" +
			"\n" +
			"\t\ttry {\n" +
			"\t\t\tPutMappingRequest request = new PutMappingRequest(this.indexName);\n" +
			"\t\t\trequest.type(\"_doc\");\n" +
			"\t\t\t// Mapping for search-able writerFieldTypeStr\n" +
			"\t\t\tJSONObject searchFields = new JSONObject();\n" +
			"\t\t\tsearchFields.put(\"type\", \"text\");\n" +
			"\t\t\tsearchFields.put(\"fielddata\", true);\n" +
			"\n" +
			"\t\t\tJSONObject searchDateFields = new JSONObject();\n" +
			"\t\t\tsearchDateFields.put(\"type\", \"date\");\n" +
			"\t\t\tsearchDateFields.put(\"format\", \"yyyyMMdd\");\n" +
			"\n" +
			"\t\t\tJSONObject searchDoubleFields = new JSONObject();\n" +
			"\t\t\tsearchDoubleFields.put(\"type\", \"double\");\n" +
			"\n" +
			"\t\t\tJSONObject searchIntFields = new JSONObject();\n" +
			"\t\t\tsearchIntFields.put(\"type\", \"integer\");\n" +
			"\n" +
			"\t\t\tJSONObject properties = new JSONObject();\n" +
			"\t\t\tproperties.put(\"id\", searchFields);\n" +
			"\t\t\tproperties.put(\"APPL_NO_PK\", searchIntFields);\n" +
			"\t\t\t/*Properties*/\n" +
			"\n" +
			"\t\t\tJSONObject itemJSON = new JSONObject();\n" +
			"\t\t\titemJSON.put(\"properties\", properties);\n" +
			"\t\t\tSystem.out.println(itemJSON);\n" +
			"\n" +
			"\t\t\tboolean indexExist = this.mClient.admin().indices().prepareExists(this.indexName.toLowerCase()).execute()\n" +
			"\t\t\t\t\t.actionGet().isExists();\n" +
			"\t\t\tif (!indexExist)\n" +
			"\t\t\t\tthis.mClient.admin().indices().prepareCreate(this.indexName.toLowerCase())\n" +
			"\t\t\t\t\t\t.setSettings(Settings.builder().put(\"index.number_of_replicas\", 1).put(\"index.number_of_shards\", numOfShards)).get();\n" +
			"\n" +
			"\t\t\tAcknowledgedResponse putMappingResponse = this.mClient.admin().indices()\n" +
			"\t\t\t\t\t.preparePutMapping(this.indexName.toLowerCase()).setType(this.indexName.toLowerCase())\n" +
			"\t\t\t\t\t.setSource(itemJSON.toString(), XContentType.JSON).get();\n" +
			"\t\t\tSystem.out.println(putMappingResponse.isAcknowledged());\n" +
			"\t\t\treturn putMappingResponse.isAcknowledged();\n" +
			"\t\t} catch (JSONException e) {\n" +
			"\t\t\tlog.error(\"Exception occured while creating mapping for the index.\", e);\n" +
			"\t\t}\n" +
			"\n" +
			"\t\treturn true;\n" +
			"\t}\n" +
			"\n" +
			"\t/*\n" +
			"\t * (non-Javadoc)\n" +
			"\t *\n" +
			"\t * @see org.springframework.batch.item.ItemWriter#write(java.util.List)\n" +
			"\t */\n" +
			"\t@Override\n" +
			"\tpublic void write(final List<? extends /*$PRODUCT+Typefull*/> items) throws Exception {\n" +
			"\t\tfor (/*$PRODUCT+Typefull*/ item : items) {\n" +
			"\t\t\tthis.bulkRequest.add(mClient.prepareIndex(indexName, indexName, item.getId()).setSource(XContentFactory\n" +
			"\t\t\t\t\t.jsonBuilder().startObject()\n" +
			"\n" +
			"\t\t\t\t\t/*Fields*/ //todo\n" +
			"\n" +
			"\t\t\t\t\t.endObject()));\n" +
			"\t\t\tif (this.bulkRequest.numberOfActions() >= 1000)\n" +
			"\t\t\t\texecuteBulkRequest(bulkRequest);\n" +
			"\t\t}\n" +
			"\t\tif (this.bulkRequest.numberOfActions() > 0)\n" +
			"\t\t\texecuteBulkRequest(bulkRequest);\n" +
			"\t}\n" +
			"\n" +
			"\t/**\n" +
			"\t * Executes an already created bulk requests and return true if indexing was\n" +
			"\t * successful\n" +
			"\t */\n" +
			"\tpublic synchronized boolean executeBulkRequest(BulkRequestBuilder bulkRequest) {\n" +
			"\t\tboolean status = false;\n" +
			"\t\tif (bulkRequest != null) {\n" +
			"\t\t\tint rec = bulkRequest.numberOfActions();\n" +
			"\t\t\tBulkResponse bulkResponse = bulkRequest.execute().actionGet();\n" +
			"\n" +
			"\n" +
			"\t\t\tif (bulkResponse.hasFailures()) {\n" +
			"\t\t\t\tlog.error(\"Some exception occured in bulk insert in ESDataResourceServiceImpl: executeBulkRequest : \"\n" +
			"\t\t\t\t\t\t+ bulkResponse.buildFailureMessage());\n" +
			"\t\t\t} else {\n" +
			"\t\t\t\tlog.info(\"ES Bulk executed for record count : \" + rec);\n" +
			"\t\t\t\tstatus = true;\n" +
			"\t\t\t}\n" +
			"\t\t\tthis.bulkRequest = null;\n" +
			"\t\t\tthis.bulkRequest = this.mClient.prepareBulk();\n" +
			"\t\t}\n" +
			"\t\tthis.bulkRequest = null;\n" +
			"\t\tthis.bulkRequest = this.mClient.prepareBulk();\n" +
			"\t\treturn status;\n" +
			"\t}\n" +
			"\n" +
			"\t@Override\n" +
			"\tpublic void afterPropertiesSet() throws Exception {\n" +
			"\t}\n" +
			"}\n";


	public static void main(String args[]){
//		String config = JavaCodeUtils.config.replace("/*$PRODUCT+Type*/", replaceUtils.PRODUCTType)
//				.replace("/*$PRODUCT+Typefull*/", replaceUtils.PRODUCTTypefull)
//				.replace("/*$product+Typefull*/", replaceUtils.productTypefull)
//				.replace("/*$product+Type*/", replaceUtils.productType)
//				.replace("/*Type*/", replaceUtils.Type)
//				.replace("/*typefull*/", replaceUtils.typefull)
//				.replace("/*product*/", replaceUtils.product)
//				.replace("/*$column names*/", replaceUtils.configFieldStr);
//		String processor = JavaCodeUtils.processor.replace("/*$PRODUCT+Type*/", replaceUtils.PRODUCTType)
//				.replace("/*$PRODUCT+Typefull*/", replaceUtils.PRODUCTTypefull)
//				.replace("/*$product+Typefull*/", replaceUtils.productTypefull)
//				.replace("/*$product+Type*/", replaceUtils.productType);
//		String writer = JavaCodeUtils.writer.replace("/*$PRODUCT+Type*/", replaceUtils.PRODUCTType)
//				.replace("/*$PRODUCT+Typefull*/", replaceUtils.PRODUCTTypefull)
//				.replace("/*$product+Typefull*/", replaceUtils.productTypefull)
//				.replace("/*$product+Type*/", replaceUtils.productType)
//				.replace("/*Fields*/", replaceUtils.writerFieldTypeStr)
//				.replace("/*Properties*/", replaceUtils.writerFieldPropertyStr);
////					System.out.println("\n\n\\n"+writer+"\n\n\n\n");
//		String dto = JavaCodeUtils.dto.replace("/*$PRODUCT+Type*/", replaceUtils.PRODUCTType)
//				.replace("/*$PRODUCT+Typefull*/", replaceUtils.PRODUCTTypefull)
//				.replace("/*$product+Typefull*/", replaceUtils.productTypefull)
//				.replace("/*$getterSetterString*/", replaceUtils.getterSetterString)
//				.replace("/*$dataString*/", replaceUtils.dataString);
//

	}


}
