package soso.production.service;

import soso.production.model.Cart;
import soso.production.model.dto.CartReportDto;
import soso.production.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Service("cartService")
public class CartService implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> findAllCartsByOwnerEmail(String email) {
        return cartRepository.findAllByCartOwner_Email(email);
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public List<CartReportDto> findAllCartReports() {
        return cartRepository.findAllCartReports();
    }

    @Override
    public CartReportDto findCartReportByCartId(Long id) {
        return cartRepository.findCartReportByCartId(id);
    }

    @Override
    public Cart findCartById(Long id) {
        return cartRepository.getOne(id);
    }
}
