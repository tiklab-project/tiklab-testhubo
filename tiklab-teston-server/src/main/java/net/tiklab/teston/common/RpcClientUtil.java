package net.tiklab.teston.common;

import net.tiklab.rpc.client.RpcClient;
import net.tiklab.rpc.client.config.RpcClientConfig;
import org.springframework.stereotype.Component;

@Component
public class RpcClientUtil {

    //rpc配置
   public RpcClient rpcClient(){
        RpcClientConfig rpcClientConfig = RpcClientConfig.instance();
        RpcClient rpcClient = new RpcClient(rpcClientConfig);
        return rpcClient;
    }
}
