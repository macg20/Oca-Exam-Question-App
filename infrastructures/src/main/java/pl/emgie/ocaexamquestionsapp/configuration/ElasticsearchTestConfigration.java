package pl.emgie.ocaexamquestionsapp.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.util.concurrent.TimeUnit.MINUTES;
import static pl.allegro.tech.embeddedelasticsearch.PopularProperties.CLUSTER_NAME;
import static pl.allegro.tech.embeddedelasticsearch.PopularProperties.TRANSPORT_TCP_PORT;

@Configuration
@Profile("test")
@EnableConfigurationProperties(ElasticsearchProperties.class)
public class ElasticsearchTestConfigration {

    private static Log logger = LogFactory.getLog(ElasticsearchTestConfigration.class);

    static final String ELASTIC_VERSION = "6.0.0";
    static final Integer TRANSPORT_TCP_PORT_VALUE = 9930;
    static final String CLUSTER_NAME_VALUE = "myTestCluster";

    @Autowired
    private ElasticsearchProperties properties;

    private NodeClient client;

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
    }

    @Bean("embeddedElastic")
    public EmbeddedElastic embeddedElastic() throws IOException, InterruptedException {
        EmbeddedElastic embeddedElastic = EmbeddedElastic.builder()
                .withElasticVersion(ELASTIC_VERSION)
                .withSetting(TRANSPORT_TCP_PORT, TRANSPORT_TCP_PORT_VALUE)
                .withSetting(CLUSTER_NAME, CLUSTER_NAME_VALUE)
                .withDownloaderConnectionTimeout(30,MINUTES)
                .withDownloaderReadTimeout(30,MINUTES)
                .withStartTimeout(10, MINUTES)
                .build()
                .start();
        return embeddedElastic;
    }

    @Bean
    @DependsOn("embeddedElastic")
    public Client client() {
        Settings elasticsearchSettings = Settings.builder()
                .put("cluster.name", CLUSTER_NAME_VALUE)
                .build();
        TransportClient client = new PreBuiltTransportClient(elasticsearchSettings);
        try {
            client.addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), TRANSPORT_TCP_PORT_VALUE));
        } catch (UnknownHostException e) {
            logger.error(e.getMessage(), e);
        }
        return client;
    }

}