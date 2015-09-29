
package com.epam.callable;


import com.epam.models.InventoryListDto;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.Callable;

/**
 * Callable to get Inventory by page number.
 * 
 * @author jbuckley
 *
 */
public class GetInventoryCallable implements Callable<InventoryListDto> {
    
    private final int pageNum;
    private final WebTarget webTarget;
    
    private static final String PAGE_NUMBER = "pageNumber";
    
    public GetInventoryCallable(WebTarget webTarget, int pageNum) {
        this.webTarget = webTarget;
        this.pageNum = pageNum;
    }
    
    @Override
    public InventoryListDto call() throws Exception {
        return webTarget
                .queryParam(PAGE_NUMBER, pageNum)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(InventoryListDto.class);
    }
}
