package com.jesus.prueba_tecnica.application.services;

import com.jesus.prueba_tecnica.infrastructure.repository.dao.PriceRepository;
import com.jesus.prueba_tecnica.domain.Price;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
     * @return {@link Optional} que contiene el {@link Price} si se encuentra, o un Optional vacío si no hay precio aplicable.
     */
    public Price getApplicablePrice(LocalDateTime applicationDate, int productId, int brandId) throws Exception {
        
        dataValidator(applicationDate, productId, brandId);

        List<Price> priceList = priceRepository.findApplicablePrice(applicationDate, productId, brandId);

        if (priceList == null || priceList.isEmpty()) {
            throw new NoSuchElementException("No price found for the given parameters.");
        }

        // Aseguramos que el resultado esté ordenado antes de obtener el primero
        priceList.sort(Comparator.comparingInt(Price::getPriority).reversed());

        return priceList.get(0); // Obtener el primer elemento garantizando que existe
    }

    /**
     * Metodo encargado de validar los datos de entrada
     * @param applicationDate La fecha en la que se va a filtrar
     * @param productId El id del producto por el que se va a filtrar
     * @param brandId El brandId por el que se va a filtrar
     */
    private void dataValidator(LocalDateTime applicationDate, int productId, int brandId) {

        if(applicationDate == null){
            throw new IllegalArgumentException("applicationDate cannot be null");
        }

        if(productId < 0){
            throw new IllegalArgumentException("productId cannot be negative");
        }

        if(brandId < 0){
            throw new IllegalArgumentException("brandId cannot be negative");
        }
    }
}
