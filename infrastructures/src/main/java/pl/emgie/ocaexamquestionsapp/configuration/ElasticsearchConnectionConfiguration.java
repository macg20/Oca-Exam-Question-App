package pl.emgie.ocaexamquestionsapp.configuration;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@EnableElasticsearchRepositories(basePackages = "pl.emgie.ocaexamquestionsapp.repository")
public class ElasticsearchConnectionConfiguration {

    Logger logger = LoggerFactory.getLogger(ElasticsearchConnectionConfiguration.class);

    private String clusterName;
    private String elasticsearchHost;
    private int elasticsearchPort;

    public ElasticsearchConnectionConfiguration(@Value("${elasticsearch.cluster.name}") String clusterName,
                                                @Value("${elasticsearch.host}") String elasticsearchHost,
                                                @Value("${elasticsearch.port}") int elasticsearchPort) {
        this.clusterName = clusterName;
        this.elasticsearchHost = elasticsearchHost;
        this.elasticsearchPort = elasticsearchPort;
    }

    @Bean
    public Client client() {

        Settings elasticsearchSettings = Settings.builder()
                .put("client.transport.sniff", true)
                .put("cluster.name", clusterName)
                .build();
        TransportClient client = new PreBuiltTransportClient(elasticsearchSettings);
        try {
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(elasticsearchHost), elasticsearchPort));
        } catch (UnknownHostException e) {
            logger.error(e.getMessage(), e);
        }
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        ElasticsearchTemplate template = new ElasticsearchTemplate(client());
        return template;
    }

}