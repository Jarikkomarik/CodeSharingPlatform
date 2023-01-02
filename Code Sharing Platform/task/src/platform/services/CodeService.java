package platform.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import platform.dtos.CodeDto;
import platform.dtos.NewCodeDto;
import platform.repos.CodeRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CodeService {

    private final CodeRepository repository;

    public CodeService(CodeRepository repository) {
        this.repository = repository;

    }

    public String addCode(NewCodeDto code) {
        var temp = new CodeDto(code.getCode(), UUID.randomUUID().toString(), code.getTime(), code.getViews());
        repository.save(temp);
        return temp.getId();

    }

    public CodeDto getCode(String id) {
        var codeDto = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (codeDto.isViewsAreLimited() || codeDto.isTimeIsLimited()) {

            //if views are limited -> update views
            if (codeDto.isViewsAreLimited()) updateViews(codeDto);
            repository.save(codeDto);
            //if time is limited -> update time
            if (codeDto.isTimeIsLimited()) codeDto.setTime(codeDto.getTime() - updateTime(codeDto));

            //if limit is breached -> throw
            if ((codeDto.isTimeIsLimited() && codeDto.getTime() <= 0) || (codeDto.isViewsAreLimited() && codeDto.getViews() < 0)) {
                repository.delete(codeDto);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
        return codeDto;

    }

    public List<CodeDto> getLatest10() {
        List<CodeDto> ret = repository.getAllByTimeIsLimitedFalseAndViewsAreLimitedFalse();

        if (ret.size() > 10) ret = ret.stream().skip(ret.size() - 10).collect(Collectors.toList());
        Collections.sort(ret, Comparator.comparing(codeDto -> LocalDateTime.parse(codeDto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))));
        Collections.reverse(ret);
        return ret;

    }

    private void updateViews(CodeDto codeDto) {
        codeDto.setViews(codeDto.getViews() - 1);
    }

    private long updateTime(CodeDto codeDto) {
        long createTime = LocalDateTime.parse(codeDto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")).toEpochSecond(ZoneOffset.ofTotalSeconds(0));
        long currentTime = LocalDateTime.now().toEpochSecond(ZoneOffset.ofTotalSeconds(0));
        return currentTime - createTime;
    }

}
