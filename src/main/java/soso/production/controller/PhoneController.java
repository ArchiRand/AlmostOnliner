package soso.production.controller;

import soso.production.model.Phone;
import soso.production.model.dto.PhoneDto;
import soso.production.service.IPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;

@Controller("phoneController")
public class PhoneController {

    @Autowired
    private IPhoneService phoneService;

    @RequestMapping(value="/user/phone", method= RequestMethod.GET)
    public String getPhoneForm(Principal principal, Model model) {
        String email = principal.getName();
        Phone userPhoneObj = phoneService.getPhoneByUserEmail(email);

        PhoneDto phoneDto = new PhoneDto(userPhoneObj.getNumber());
        model.addAttribute("phoneForm", phoneDto);
        return "phone/editPhone";
    }

    @RequestMapping(value="/user/phone", method=RequestMethod.POST)
    public String editPhone(Principal principal, @Valid @ModelAttribute("phoneForm") PhoneDto phoneDto, BindingResult result) {
        if (result.hasErrors()) {
            return "phone/editPhone";
        }

        String email = principal.getName();
        Phone userPhoneObj = phoneService.getPhoneByUserEmail(email);

        userPhoneObj.setNumber(phoneDto.getNumber());
        phoneService.save(userPhoneObj);
        return "redirect:/user";
    }
}
