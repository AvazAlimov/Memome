package uz.nasiba.avaz.memome.db.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uz.nasiba.avaz.memome.db.retrofit.service.AccountService;
import uz.nasiba.avaz.memome.db.retrofit.service.MemoryService;

public class AppRetrofit {
    private AccountService accountService;
    private MemoryService memoryService;

    public AppRetrofit() {
        Retrofit instance = new Retrofit.Builder()
                .baseUrl("http://nasiba.me:8000/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        accountService = instance.create(AccountService.class);
        memoryService = instance.create(MemoryService.class);
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public MemoryService getMemoryService() {
        return memoryService;
    }
}
