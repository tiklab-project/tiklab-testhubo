package io.thoughtware.teston.common;

import io.thoughtware.rpc.client.RpcClient;
import io.thoughtware.rpc.client.config.RpcClientConfig;
import org.springframework.stereotype.Component;

/**
 * rpc
 */
@Component
public class RpcClientUtil {

    //rpc配置
   public RpcClient rpcClient(){
        RpcClientConfig rpcClientConfig = RpcClientConfig.instance();
        RpcClient rpcClient = new RpcClient(rpcClientConfig);
        return rpcClient;
    }
}
