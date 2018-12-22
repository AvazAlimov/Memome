package uz.nasiba.avaz.memome.db.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uz.nasiba.avaz.memome.db.retrofit.service.AccountService;
import uz.nasiba.avaz.memome.db.retrofit.service.MemoryService;

//Nasiba: retrofit component
public class AppRetrofit {
    private AccountService accountService;
    private MemoryService memoryService;

    //create one time instance and web services
    public AppRetrofit() {
        Retrofit instance = new Retrofit.Builder()
                .baseUrl("http://nasiba.me:8000/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        accountService = instance.create(AccountService.class);
        memoryService = instance.create(MemoryService.class);
    }

    //return account services
    public AccountService getAccountService() {
        return accountService;
    }

    //return memory services
    public MemoryService getMemoryService() {
        return memoryService;
    }
}
