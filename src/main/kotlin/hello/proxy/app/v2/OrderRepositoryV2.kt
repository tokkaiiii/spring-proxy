package hello.proxy.app.v2

import java.lang.Thread.*

open class OrderRepositoryV2 {


    open fun save(itemId: String){
        if(itemId == "ex"){
            throw IllegalArgumentException("itemId can't be null")
        }
        sleep(1000)
    }

}