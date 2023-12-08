package io.thoughtware.teston.test.app.utils;

import io.thoughtware.rpc.client.RpcClient;
import io.thoughtware.rpc.client.config.RpcClientConfig;
import org.springframework.stereotype.Component;

/**
 * rpc
 */
@Component
public class RpcClientAppUtil {

    //rpc配置
   public RpcClient rpcClient(){
        RpcClientConfig rpcClientConfig = RpcClientConfig.instance();
        RpcClient rpcClient = new RpcClient(rpcClientConfig);
        return rpcClient;
    }
}
