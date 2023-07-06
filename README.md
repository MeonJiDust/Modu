# 💡주제

#### ▸ 장애인 취업 지원 어플리케이션<br/><br/>

# 📝 배경

비장애인들은 잡코리아등의 어플로 비교적 쉽게 구직을 할 수 있다. 

하지만 장애인들은 직업을 구하려면 인터넷 웹사이트로 들어가야 모집 공고 글을 볼 수 있으며 이마저도 장애별로 카테고리들이 정확히 나눠져있지 않아 구직에 어려움을 느낄 수 있다고 생각되어 본 어플리케이션을 개발하게되었다. <br/><br/>

# 📝 주요 Activity

▸ 회원가입 Activity

<img src="https://github.com/MeonJiDust/Modu/assets/90547127/595c7b31-0510-492c-9183-099d1ebe57d4"  width="200" height="400">
<img src="https://github.com/MeonJiDust/Modu/assets/90547127/7419b699-63ee-4f29-9cb3-73888503996e"  width="200" height="400">
<img src="https://github.com/MeonJiDust/Modu/assets/90547127/6a3fcd46-c4d7-43d8-94df-cb67afb2d348"  width="200" height="400"><br/><br/>

▸ 지원 Activity - 장애명을 선택하면 해당 장애를 가진 사람들이 지원할 수 있는 곳들이 리스트업 된다.

<img src="https://github.com/MeonJiDust/Modu/assets/90547127/4de93395-830f-4c1e-8df9-82e614178795"  width="200" height="400">
<img src="https://github.com/MeonJiDust/Modu/assets/90547127/cfd84659-8b1c-4efb-95c3-c16a918ec444"  width="200" height="400">
<img src="https://github.com/MeonJiDust/Modu/assets/90547127/32d05f48-c567-471e-ad10-815ea5ba20e4"  width="200" height="400"><br/><br/>

▸ 소통 Activity - 사용자들끼리 서로 취업정보 등을 공유할 수 있는 커뮤니

<img src="https://github.com/MeonJiDust/Modu/assets/90547127/b192bf89-9f43-4224-ab92-8bd562f9e35d"  width="200" height="400">
<img src="https://github.com/MeonJiDust/Modu/assets/90547127/a0f23de7-80c1-4148-b2d9-096c23224255"  width="200" height="400"><br/><br/>

# ⭐️ 주요 코드 리뷰

▸ 파이어베이스의 Authentication과 Realtime Database기능을 사용하여 회원가입 기능을 구현하였다. 

UserAccount 클래스에는 유저 정보를 등록하는데 사용할 getter와 setter, 생성자가 들어있다. 

realtime database에 "UserAccount" 밑에 유저의 uid명 밑에 유저 정보들을 저장함.
```
mAuth = FirebaseAuth.getInstance();
mDatabase = FirebaseDatabase.getInstance();
mReference = mDatabase.getInstance().getReference();

FirebaseUser user = mAuth.getCurrentUser();
UserAccount account = new UserAccount();

account.setUid(user.getUid());
account.setEmail(user.getEmail());
account.setPassword(pw);
account.setName(name);
account.setBirth(birth);
account.setPhone(phone);

mReference.child("UserAccount").child(user.getUid()).setValue(account);
```
<br/><br/>
▸ 라디오버튼은 다중선택이 되지 않아, 여러 장애를 가지고 있는 사람들이 여러 버튼을 선택할 수 없었다. 따라서 체크박스로 선택 화면을 바꾸었고, 체크박스가 해제되면 list에 저장된 "장애명"을 삭제하고, 모든 선택이 완료 되면 realtime database에 "disability name" -> "유저 uid" 하위에 선택한 장애명들이 담긴 리스트를 저장한다.

```
ArrayList<String> list = new ArrayList<>();
/*...*/
CheckBox button_자폐성장애 = (CheckBox) findViewById(R.id.btn_자폐성장애);
        CheckBox button_정신장애 = (CheckBox) findViewById(R.id.btn_정신장애);
        DisabilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select.clear();
                if (button_지체장애.isChecked()) {
                    list.add("지체장애");
                }else{
                    list.remove("지체장애");
                }
                /*...*/
                mReference.child("disability name").child(mAuth.getUid()).setValue(list);
```
<br/><br/>

# 📺 구동 영상

[![Video Label](http://img.youtube.com/vi/DsZBbVkaedg/0.jpg)](https://www.youtube.com/watch?v=DsZBbVkaedg)
# 🤔 배운점

▸ 안드로이드 스튜디오의 라이브러리 중에 SharedPreferences라는 라이브러리를 새로 알게 되었고, 이후 프로젝트 진행 중에도 이번 같은 상황( DB를 쓰기엔 너무 작은 데이터를 저장해야할 때 )이 생기면 SharedPreferences를 사용하고 있다.

▸ 키보드 밖의 화면을 터치하면 키보드가 내려가는 것과 같은 사용자가 느낄 수 있는 자잘한 오류들의 존재를 알게 되었다.


