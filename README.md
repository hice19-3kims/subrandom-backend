## 개발환경
- java 17
- spring boot 3.2.2
- gradle - groovy project
- Jar packaging
### dependencies
- spring data JPA
- thymeleaf
- spring web
- spring boot devtools
- validation
- lombok
- mariadb
## 엔티티 컨벤션
- 엔티티 생성자는 모든 필드를 포함하며, 생성자 단위에서 @Builder를 사용한다. 단, 객체 생성 시 생성자는 사용하지 않고 Builder를 사용한다.
- 레시피 객체를 생성할 때에는 레시피재료 필드를 제외하고 초기화해 생성한다. 그 이후에 레시피재료 객체의 생성 메소드를 사용한다.
- 레시피 수정 기능 등으로의 확장성을 고려해 레시피재료 엔티티에 연관관계 편의 메소드가 포함되었다. 단, 기능 추가 결정 전이나 특별한 이유가 없는 경우에는 해당 메소드들을 사용하지 않는다.
## 특이사항
- 해킹 방지를 위해 gitignore에 application.yml(application.properties)이 포함되어 있습니다. 로컬에서 테스트하실 때에는 직접 작성하셔서 사용 바랍니다.