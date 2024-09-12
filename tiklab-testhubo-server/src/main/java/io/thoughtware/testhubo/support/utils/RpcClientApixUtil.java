package io.thoughtware.testhubo.support.utils;

import io.thoughtware.rpc.client.RpcClient;
import io.thoughtware.rpc.client.config.RpcClientConfig;
import org.springframework.stereotype.Component;

/**
 * rpc实例
 */
@Component
public class RpcClientApixUtil {

    //rpc配置
   public RpcClient rpcClient(){
        RpcClientConfig rpcClientConfig = RpcClientConfig.instance();
        RpcClient rpcClient = new RpcClient(rpcClientConfig);
        return rpcClient;
    }
}
