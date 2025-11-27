package com.UH.OtherLevel.infrastructure.adapter.out.persistence.adapter.config;





import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Component
public class TransactionalUseCaseExecutor {

    @Transactional
    public <T> T executeInTransaction (Supplier<T> useCase){
        return  useCase.get();
    }

    @Transactional(readOnly = true)
    public <T> T executeReadOnly(Supplier<T> useCase){
        return  useCase.get();
    }

    @Transactional
    public void executeInTransactionVoid(Runnable useCase){
        useCase.run();
    }
}
