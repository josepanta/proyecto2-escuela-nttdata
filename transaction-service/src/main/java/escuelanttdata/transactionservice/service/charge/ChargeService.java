package escuelanttdata.transactionservice.service.charge;

import escuelanttdata.transactionservice.model.charge.Charge;
import io.reactivex.Completable;
import io.reactivex.Maybe;

import java.util.List;
import java.util.Optional;

public interface ChargeService {

    Completable save(Charge charge);

    Maybe<Optional<List<Charge>>> getChargeByProductId(Integer id);
}
