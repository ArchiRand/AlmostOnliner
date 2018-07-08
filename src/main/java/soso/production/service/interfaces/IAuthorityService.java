package soso.production.service.interfaces;

import soso.production.model.Authority;

public interface IAuthorityService {
    Authority getByAuthority(String authority);
    Authority getById(Long id);
}
