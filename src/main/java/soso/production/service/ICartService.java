package soso.production.service;

import soso.production.model.Cart;
import soso.production.model.dto.CartReportDto;

import java.util.List;

public interface ICartService {
    Cart save(Cart cart);
    List<Cart> findAllCartsByOwnerEmail(String email);
    List<Cart> findAll();
    List<CartReportDto> findAllCartReports();
    CartReportDto findCartReportByCartId(Long id);
    Cart findCartById(Long id);
}
