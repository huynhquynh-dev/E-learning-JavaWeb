package com.myclass.service.impl;

import com.myclass.model.entity.Account;
import com.myclass.model.entity.AccountRole;
import com.myclass.model.mapper.AccountMapper;
import com.myclass.model.request.AccountRequest;
import com.myclass.repository.AccountRepository;
import com.myclass.repository.AccountRoleRepository;
import com.myclass.service.AccountService;
import com.myclass.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    AccountRoleRepository accountRoleRepository;

    @Autowired
    private PasswordEncoder userPasswordEncoder;

    @Autowired
    PasswordUtil passwordUtil;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(long accountId) {
        Optional<Account> optional = accountRepository.findById(accountId);
        return optional.orElse(null);
    }

    @Override
    public Account saveOrUpdate(AccountRequest accountRequest) {
        Account account = null;
        String password = null;
        String passwordEncode = null;
        if (accountRequest.getId() == 0) {
            account = new Account();
            password = passwordUtil.alphaNumericString(8);
            passwordEncode = userPasswordEncoder.encode(password);
            account.setPassword(passwordEncode);
        }
        else {
            Optional<Account> optional = accountRepository.findById(accountRequest.getId());
            if (optional.isPresent()) {
                account = optional.get();
            }
        }
        account = accountMapper.toAccountEntity(account, accountRequest);
        account = accountRepository.save(account);
        List<AccountRole> accountRoles = accountMapper.toListAccountRoleEntity(account, accountRequest.getRoles());
        accountRoleRepository.saveAll(accountRoles);
        if (accountRequest.getId() == 0) {
            try {
                Map model = new HashMap();
                model.put("accountName", account.getFullName());
                model.put("accountUsername", account.getUsername());
                model.put("accountPassword", password);

                Context context = new Context();
                context.setVariables(model);
                String html = templateEngine.process("mail_template_account", context);

                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message,
                        MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                        StandardCharsets.UTF_8.name());
                helper.setTo(account.getEmail());
                helper.setSubject("API_MAIL");
                helper.setText(html, true);
                javaMailSender.send(message);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return account;
    }

    @Override
    public boolean delete(long accountId) {
        Optional<Account> optional = accountRepository.findById(accountId);
        if (optional.isPresent()) {
            Account account = optional.get();
            accountRepository.delete(account);
            return true;
        }
        return false;
    }
}
