package com.mentoring.demo.mentoring.adaptor.out.csv;

import com.mentoring.demo.mentoring.application.port.in.dto.in.AddMentoringSessionDto;
import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringAddRequestDto;
import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringCategoryDto;
import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringHashTagDto;
import com.mentoring.demo.mentoring.application.port.out.MentoringCsvDataOutPort;
import com.mentoring.demo.mentoring.common.entity.BaseResponseStatus;
import com.mentoring.demo.mentoring.common.exception.BaseException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Component
public class MentoringCsvDataAdapter implements MentoringCsvDataOutPort {

    @Override
    public List<MentoringAddRequestDto> MentoringCsvDataParser(MultipartFile file) {
        List<MentoringAddRequestDto> mentoringAddRequestDtos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withDelimiter(';') // 구분자를 ';'로 설정
                     .withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser.getRecords()) {
                try {
                    String name = record.get("name").trim();
                    String description = record.get("description").trim();
                    String detail = record.get("detail").trim();
                    String mentorUuid = record.get("mentorUuid").trim();
                    String thumbnailUrl = record.get("thumbnailUrl").trim();

                    // 객체 생성
                    MentoringAddRequestDto mentoringAddRequestDto = MentoringAddRequestDto.builder()
                            .name(name)
                            .description(description)
                            .detail(detail)
                            .mentorUuid(mentorUuid)
                            .thumbnailUrl(thumbnailUrl)
                            .isReusable(true)
                            .sessionList(addMentoringSession())
                            .categoryList(generateCategoryList(name))
                            .hashTagList(generateHashTagList(name))
                            .build();

                    mentoringAddRequestDtos.add(mentoringAddRequestDto);
                } catch (Exception innerEx) {
                    // 필드 매핑 중 발생한 예외를 로깅
                    System.err.println("Error parsing record: " + record + " | Exception: " + innerEx.getMessage());
                }
            }
        } catch (IOException e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }

        return mentoringAddRequestDtos;
    }

    public List<AddMentoringSessionDto> addMentoringSession() {
        List<AddMentoringSessionDto> sessions = new ArrayList<>();
        Random random = new Random();

        LocalDate startDate = LocalDate.of(2024, 12, 11);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        int targetSessionCount = 10; // 멘토링당 10개의 세션
        int currentSessionCount = 0;

        LocalDate currentDate = startDate;
        while (currentDate.isBefore(endDate.plusDays(1)) && currentSessionCount < targetSessionCount) {
            // 하루에 2-3개의 세션 생성
            int sessionsPerDay = random.nextInt(2) + 2; // 2 or 3

            // 가능한 시작 시간대 (9시-20시)
            List<Integer> availableHours = new ArrayList<>();
            for (int i = 9; i <= 20; i++) {
                availableHours.add(i);
            }

            // 하루의 세션들 생성
            for (int i = 0; i < sessionsPerDay && currentSessionCount < targetSessionCount && !availableHours.isEmpty(); i++) {
                // 랜덤하게 시작 시간 선택
                int hourIndex = random.nextInt(availableHours.size());
                int startHour = availableHours.get(hourIndex);

                // 세션 길이 (1-3시간)
                int sessionLength = random.nextInt(3) + 1;

                LocalTime startTime = LocalTime.of(startHour, 0);
                LocalTime endTime = startTime.plusHours(sessionLength);

                // 겹치는 시간대 제거
                for (int j = startHour; j < startHour + sessionLength && j <= 20; j++) {
                    availableHours.remove(Integer.valueOf(j));
                }

                AddMentoringSessionDto session = AddMentoringSessionDto.builder()
                        .startDate(currentDate)
                        .endDate(currentDate)
                        .startTime(startTime)
                        .endTime(endTime)
                        .deadlineDate(currentDate.minusDays(1))
                        .minHeadCount(random.nextInt(3) + 1) // 1-3명
                        .maxHeadCount(Math.min(random.nextInt(7) + 4, 10)) // 4-10명
                        .price(random.nextInt(901) + 100) // 100-1000
                        .build();

                sessions.add(session);
                currentSessionCount++;
            }

            currentDate = currentDate.plusDays(1);
        }

        return sessions;
    }

    private static final Map<String, String> DOMAIN_CATEGORY_MAP = new HashMap<>() {{
        put("이력서", "TC-CD7877C0");
        put("자소서", "TC-8C93C5F5");
        put("면접", "TC-0489394A");
        put("포트폴리오", "TC-8E506504");
    }};

    private static final Map<String, String> JOB_CATEGORY_MAP = new HashMap<>() {{
        put("개발", "TC-8ACBA7E8");
        put("네카라쿠배", "TC-8ACBA7E8");
        put("AI", "TC-8ACBA7E8");
        put("IT", "TC-8ACBA7E8");
        put("디자인", "TC-AC2476E1");
        put("디자이너", "TC-AC2476E1");
        put("UI", "TC-AC2476E1");
        put("UX", "TC-AC2476E1");
        put("마케팅", "TC-8B337FE8");
        put("마케터", "TC-8B337FE8");
        put("영업", "TC-FBC0B657");
        put("인사", "TC-806F308B");
        put("금융", "TC-A2C6F50B");
        put("은행", "TC-A2C6F50B");
        put("펀드", "TC-A2C6F50B");
        put("교육", "TC-E9363961");
        put("대학", "TC-E9363961");
        put("논문", "TC-E9363961");
        put("과제", "TC-E9363961");
        put("의료", "TC-3837D5AC");
        put("간호", "TC-3837D5AC");
        put("연구", "TC-2A97B0CF");
        put("연구원", "TC-2A97B0CF");
        put("제약", "TC-2A97B0CF");
        put("연구직", "TC-2A97B0CF");
        put("배터리", "TC-2A97B0CF");
        put("배터리회사", "TC-2A97B0CF");
        put("반도체", "TC-2A97B0CF");
        put("데이터", "TC-2A97B0CF");
    }};

    private List<MentoringCategoryDto> generateCategoryList(String name) {
        List<MentoringCategoryDto> categoryList = new ArrayList<>();
        Set<String> addedCategories = new HashSet<>();

        // 1. 도메인 카테고리 매칭
        String domainCategoryCode = selectDomainCategory(name);
        if (domainCategoryCode != null && addedCategories.add(domainCategoryCode)) {
            categoryList.add(createCategoryDto(domainCategoryCode));
        }

        // 2. 직군 카테고리 매칭
        String jobCategoryCode = selectJobCategory(name.toLowerCase());
        if (jobCategoryCode != null && addedCategories.add(jobCategoryCode)) {
            categoryList.add(createCategoryDto(jobCategoryCode));
        }

        // 3. 기본값으로 "전체" 추가 (아무것도 매칭되지 않은 경우)
        if (categoryList.size() < 2) {
            categoryList.add(createCategoryDto("TC-B4CD8B59")); // "전체" 카테고리
        }

        return categoryList;
    }

    private String selectDomainCategory(String name) {
        for (Map.Entry<String, String> entry : DOMAIN_CATEGORY_MAP.entrySet()) {
            if (name.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null; // 매칭되는 도메인 카테고리가 없는 경우 null 반환
    }

    private String selectJobCategory(String name) {
        for (Map.Entry<String, String> entry : JOB_CATEGORY_MAP.entrySet()) {
            if (name.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null; // 매칭되는 직군 카테고리가 없는 경우 null 반환
    }

    private MentoringCategoryDto createCategoryDto(String categoryCode) {
        Map<String, String> categoryNames = new HashMap<>();
        categoryNames.put("TC-CD7877C0", "이력서");
        categoryNames.put("TC-8C93C5F5", "자소서");
        categoryNames.put("TC-0489394A", "면접");
        categoryNames.put("TC-8E506504", "포트폴리오");
        categoryNames.put("TC-8ACBA7E8", "IT개발·데이터");
        categoryNames.put("TC-AC2476E1", "디자인");
        categoryNames.put("TC-8B337FE8", "마케팅·홍보·조사");
        categoryNames.put("TC-B4CD8B59", "전체");

        return MentoringCategoryDto.builder()
                .topCategoryCode(categoryCode)
                .topCategoryName(categoryNames.getOrDefault(categoryCode, "전체"))
                .build();
    }

    private List<MentoringHashTagDto> generateHashTagList(String name) {
        List<MentoringHashTagDto> hashTagList = new ArrayList<>();
        name = name.toLowerCase();

        Map<String, List<String>> keywordToHashtags = new HashMap<>();
        keywordToHashtags.put("자소서", Arrays.asList("자기소개서작성", "자소서첨삭", "자소서피드백", "지원동기작성"));
        keywordToHashtags.put("이력서", Arrays.asList("이력서작성", "이력서첨삭", "이력서디자인", "경력기술"));
        keywordToHashtags.put("포트폴리오", Arrays.asList("포트폴리오작성", "포트폴리오디자인", "포트폴리오피드백", "포트폴리오구성"));
        keywordToHashtags.put("면접", Arrays.asList("모의면접", "면접기술", "면접질문준비", "면접태도"));
        keywordToHashtags.put("개발", Arrays.asList("직무이해", "경험기술서", "포트폴리오강화", "디지털포트폴리오"));
        keywordToHashtags.put("디자인", Arrays.asList("포트폴리오디자인", "디지털포트폴리오", "이력서디자인", "포트폴리오구성"));
        keywordToHashtags.put("마케팅", Arrays.asList("경험기술서", "직무이해", "지원동기작성", "포트폴리오강화"));

        Set<String> selectedHashtags = new LinkedHashSet<>();

        for (Map.Entry<String, List<String>> entry : keywordToHashtags.entrySet()) {
            if (name.contains(entry.getKey())) {
                selectedHashtags.addAll(entry.getValue());
            }
        }

        if (selectedHashtags.isEmpty() || selectedHashtags.size() < 4) {
            selectedHashtags.addAll(Arrays.asList("직무이해", "경험기술서", "지원동기작성", "면접후기"));
        }

        Map<String, Integer> hashtagIdMap = new HashMap<>();
        hashtagIdMap.put("자기소개서작성", 1);
        hashtagIdMap.put("자소서첨삭", 2);
        hashtagIdMap.put("자소서피드백", 3);
        hashtagIdMap.put("지원동기작성", 4);
        hashtagIdMap.put("직무이해", 5);
        hashtagIdMap.put("경험기술서", 6);
        hashtagIdMap.put("이력서작성", 7);
        hashtagIdMap.put("이력서첨삭", 8);
        hashtagIdMap.put("이력서디자인", 9);
        hashtagIdMap.put("경력기술", 10);
        hashtagIdMap.put("포트폴리오작성", 13);
        hashtagIdMap.put("포트폴리오디자인", 14);
        hashtagIdMap.put("포트폴리오피드백", 15);
        hashtagIdMap.put("포트폴리오강화", 16);
        hashtagIdMap.put("디지털포트폴리오", 17);
        hashtagIdMap.put("포트폴리오구성", 18);
        hashtagIdMap.put("모의면접", 19);
        hashtagIdMap.put("면접기술", 20);
        hashtagIdMap.put("면접질문준비", 21);
        hashtagIdMap.put("면접태도", 23);
        hashtagIdMap.put("면접후기", 24);

        List<String> finalHashtags = new ArrayList<>(selectedHashtags);
        if (finalHashtags.size() > 6) {
            Collections.shuffle(finalHashtags);
            finalHashtags = finalHashtags.subList(0, 6);
        }

        for (String hashtagName : finalHashtags) {
            Integer hashtagId = hashtagIdMap.get(hashtagName);
            if (hashtagId != null) {
                hashTagList.add(MentoringHashTagDto.builder()
                        .hashtagId(String.valueOf(hashtagId))
                        .hashtagName(hashtagName)
                        .build());
            }
        }

        return hashTagList;
    }
}
