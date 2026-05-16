package ng.saywich.orderservice.infrastructure.grpc.abstractions;

import ng.saywich.shared.grpc.carstock.StockCarServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
public class StorageGrpcClientConfig {
    @Bean
    public StockCarServiceGrpc.StockCarServiceBlockingStub stockCarBlockingStub(
        GrpcChannelFactory channels
    ) {
        return StockCarServiceGrpc.newBlockingStub(
            channels.createChannel("storage-service")
        );
    }
}
