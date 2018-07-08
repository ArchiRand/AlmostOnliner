package soso.production.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import soso.production.model.Address;
import soso.production.model.dto.AddressDto;
import soso.production.service.interfaces.IAddressService;

import javax.validation.Valid;
import java.security.Principal;

@Controller("addressController")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @RequestMapping(value = "/user/address", method = RequestMethod.GET)
    public String getAddressForm(Principal principal, Model model) {
        String email = principal.getName();
        Address address = addressService.getAddressByUserEmail(email);
        model.addAttribute("addressForm", _fromEntityToDto(address));
        return "address/editAddress";
    }

    @RequestMapping(value = "/user/address", method = RequestMethod.POST)
    public String editAddress(Principal principal, @Valid @ModelAttribute("addressForm") AddressDto addressDto, BindingResult result) {
        if (result.hasErrors()) {
            return "address/editAddress";
        }
        String email = principal.getName();
        addressService.save(_fromDtoToEntity(addressDto, email));
        return "redirect:/user";
    }

    private AddressDto _fromEntityToDto(Address address) {
        String userCity = address.getCity();
        String userStreet = address.getStreet();
        String userBuildingNumber = address.getBuildingNumber();
        String userPostalCode = address.getPostalCode();
        return new AddressDto(userStreet, userCity, userBuildingNumber, userPostalCode);
    }

    private Address _fromDtoToEntity(AddressDto addressDto, String email) {
        Address address = addressService.getAddressByUserEmail(email);
        address.setCity(addressDto.getCity());
        address.setBuildingNumber(addressDto.getBuildingNumber());
        address.setStreet(addressDto.getStreet());
        address.setPostalCode(addressDto.getPostalCode());
        return address;
    }
}
