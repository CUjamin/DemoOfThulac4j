package cuj.demo.thulac4j.controller;

import cuj.demo.thulac4j.service.ThulacService;
import io.github.yizhiru.thulac4j.term.TokenItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: cujamin
 * @Date: 2019/1/16 18:51
 * @Description:
 */
@RestController
@RequestMapping(value = "/v1/thulac4j" , produces = "application/json")
@Api(value = "",tags = "",description = "")
@Log4j
public class ThulacController {

    @Autowired
    private ThulacService thulacService;

    @ApiOperation(value = "",notes = "")
    @RequestMapping(method = RequestMethod.POST, value = "/tagging" )
    public ThulacResponse taggering(@ApiParam(value="ThulacRequest", required=true)@RequestBody ThulacRequest thulacRequest ){
        ThulacResponse thulacResponse = new ThulacResponse();

        Map<String,List<TokenItem>> thulacResult = new HashMap<String,List<TokenItem>>();

        for(String sentence:thulacRequest.getSentenceList()){
            thulacResult.put(sentence,thulacService.tagging(sentence));
        }

        thulacResponse.setCode(1);
        thulacResponse.setMsg("success");
        thulacResponse.setData(thulacResult);

        return thulacResponse;
    }





}
