package io.tiklab.teston.integratedpostin.postinapi.service;

import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.postin.api.apix.model.ApixQuery;
import io.tiklab.postin.api.http.definition.model.HttpApi;
import io.tiklab.postin.api.http.definition.service.HttpApiService;
import io.tiklab.rpc.client.router.lookup.FixedLookup;
import io.tiklab.teston.integratedpostin.postinurl.model.PostinUrl;
import io.tiklab.teston.integratedpostin.postinurl.model.PostinUrlQuery;
import io.tiklab.teston.integratedpostin.postinurl.service.PostinUrlService;
import io.tiklab.teston.support.utils.RpcClientApixUtil;
import io.tiklab.teston.integratedpostin.postinapi.PostInApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class PostInApiServiceImpl implements PostInApiService {

    @Autowired
    RpcClientApixUtil rpcClientApixUtil;

    @Autowired
    PostinUrlService postinUrlService;

    /**
     * rpc 调用
     */
    HttpApiService httpApiServiceRpc(){
        List<PostinUrl> postinUrlList = postinUrlService.findPostinUrlList(new PostinUrlQuery().setUserId(LoginContext.getLoginId()));
        return rpcClientApixUtil.rpcClient().getBean(HttpApiService.class, new FixedLookup(postinUrlList.get(0).getUrl()));
    }


    @Override
    public List<HttpApi> findPostInApiList(ApixQuery apixQuery) {

        List<HttpApi> httpApiListByApix = httpApiServiceRpc().findHttpApiListByApix(apixQuery);

        return httpApiListByApix;
    }


}
