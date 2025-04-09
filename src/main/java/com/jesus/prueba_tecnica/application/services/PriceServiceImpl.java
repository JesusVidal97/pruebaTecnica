package com.jesus.prueba_tecnica.application.services;

import com.jesus.prueba_tecnica.application.exceptions.PriceNotFoundException;
import com.jesus.prueba_tecnica.infrastructure.repository.PriceRepository;
import com.jesus.prueba_tecnica.domain.Price;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/**
 * Clase de servicio encargada de la logica de las operaciones
 * @author Jesus
 */
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    /**
     * Metodo que se encarga de llamar al repositorio para que filtre y obtenga el precio
     *
     * @param applicationDate La fecha en la que se va a filtrar
     * @param productId       El id del producto por el que se va a filtrar
     * @param brandId         El brandId por el que se va a filtrar
     * @return {@link Price}  Devuelve el precio encontrado.
     */
    public Price getApplicablePrice(LocalDateTime applicationDate, int productId, int brandId) {

        List<Price> priceList = priceRepository.findApplicablePrice(applicationDate, productId, brandId);

        if (priceList == null || priceList.isEmpty()) {
            throw new PriceNotFoundException("No price found for product " + productId + " and brand " + brandId);
        }

        // Ordenamos la lista de precios obtenida por prioridad descendente
        priceList.sort(Comparator.comparingInt(Price::getPriority).reversed());

        return priceList.get(0);
    }


}
