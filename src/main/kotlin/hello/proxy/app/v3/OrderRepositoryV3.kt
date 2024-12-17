package hello.proxy.app.v3

import org.springframework.stereotype.Repository
import java.lang.Thread.*

@Repository
class OrderRepositoryV3 {


    fun save(itemId: String){
        if(itemId == "ex"){
            throw IllegalArgumentException("itemId can't be null")
        }
        sleep(1000)
    }

}