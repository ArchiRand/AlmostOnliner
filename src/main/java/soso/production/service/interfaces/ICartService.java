package soso.production.service.interfaces;

import soso.production.model.Cart;
import soso.production.model.dto.AdminCardDto;

import java.util.List;

public interface ICartService {
    Cart save(Cart cart);
    List<Cart> findAllCartsByOwnerEmail(String email);
    List<AdminCardDto> findAllAdminCart();
    AdminCardDto findAdminCartByCartId(Long id);
    Cart findCartById(Long id);
}
