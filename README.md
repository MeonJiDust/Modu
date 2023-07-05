# 💡주제

#### ▸ 장애인 취업 지원 어플리케이션<br/><br/>

# 📝 배경

비장애인들은 잡코리아등의 어플로 비교적 쉽게 구직을 할 수 있다. 

하지만 장애인들은 직업을 구하려면 인터넷 웹사이트로 들어가야 모집 공고 글을 볼 수 있으며 이마저도 장애별로 카테고리들이 정확히 나눠져있지 않아 구직에 어려움을 느낄 수 있다고 생각되어 본 어플리케이션을 개발하게되었다. <br/><br/>

# 📝 주요 Activity

▸ 회원가입 Activity

<img src="https://github.com/MeonJiDust/Crop/assets/90547127/34edf743-efc7-41f7-88d8-459b84ebfb63"  width="200" height="400">
<img src="https://github.com/MeonJiDust/Crop/assets/90547127/2f6bba42-f84d-41ba-a6ab-992d9fd20053"  width="200" height="400"><br/><br/>

▸ 작물선택 Activity, 판매작물 list Activity ( 작물선택 Activity에서 선택한 작물에 따라, 판매작물list Activity의 상단 멘트가 바뀜 )

<img src="https://github.com/MeonJiDust/Crop/assets/90547127/2ce53865-6aa0-4d2e-a5c8-14b93941da8e"  width="200" height="400">
<img src="https://github.com/MeonJiDust/Crop/assets/90547127/3cd654e7-1e86-45a7-a409-76c0bd806ccd"  width="200" height="400">
<img src="https://github.com/MeonJiDust/Crop/assets/90547127/4cd43139-04a1-4a94-a471-f72bf15159d2"  width="200" height="400"><br/><br/>

▸ 작물등록 Activity

<img src="https://github.com/MeonJiDust/Crop/assets/90547127/e02dab80-7d6c-49f0-aea7-e9aaee5ce1a5"  width="200" height="400">
<img src="https://github.com/MeonJiDust/Crop/assets/90547127/e00e5270-4949-48d3-8013-3fab8e54538a"  width="200" height="400">
<img src="https://github.com/MeonJiDust/Crop/assets/90547127/85f62e3a-a4cd-42ce-8bd1-75b0862fb6c6"  width="200" height="400"><br/><br/>

# ⭐️ 주요 코드 리뷰

▸ 작물 등록 화면에서 '작물선택'버튼을 누르면 작물을 선택할 수 있는 다이얼로그가 띄워지게 되고 작물을 선택하면, **해당 작물에 대한 현재 시세를 볼 수 있는** 기능
```
builder = new AlertDialog.Builder(EnrollVegetActivity.this);
        builder.setTitle("작물을 선택하세요");
        builder.setItems(crops, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                btn_select_food_crops.setText(crops[i]);
                crops_view.setText(quote[i] + "   ");
            }
        });
```
<br/><br/>
▸ 이전 화면에서 선택한 작물의 종류에 따라 그 다음 화면의 상단 사진이 바뀌어야하는데, 작물마다 Activity를 따로 쓰기엔 너무 비효율적인 것 같았다. 

그래서 **SharedPreferences 라이브러리를 사용**하였다.

이전 Activity에서 선택 작물에 대한 태그값을 putExtra로 다음 Activity로 넘겨주고, 같은 태그값을 SharedPreferences에 저장한다. 다음 Activity에서는 받은 태그값으로 선택 작물에 따라 각각 다른 상단사진을 띄울 수 있게된다.

```
//이전 Activity에서 2번째 작물 선택
imageView2.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View veiw){
        Intent intent = new Intent(VegetSelectActivity.this, CropListActivity.class);
                intent.putExtra("tag", 1);
                sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("tag", 1);
                editor.commit();
                startActivity(intent);
        }
});
```
<br/><br/>
▸ 회원가입 Activity에서, 판매자는 자신이 농부임을 증명하는 서류를 제출할 수 있는 파일 선택 버튼이 필요하지만, 구매자는 해당 버튼이 필요없다.

따라서 판매자와 구매자를 선택할 수 있는 라디오버튼에서 해당 버튼을 보일지 말지를 결정할 수 있도록 작성하였다.

```
public void onCheckedChanged(RadioGroup radioGroup, int i) {

        if (i == R.id.seller){
                user_type[0] = "판매자";
                file_name.setVisibility(View.VISIBLE);
                select_file.setVisibility(View.VISIBLE);
        }else if(i == R.id.buyer){
                user_type[0] = "구매자";
                file_name.setVisibility(View.INVISIBLE);
                select_file.setVisibility(View.INVISIBLE);
        }
}
```
<br/><br/>
# 🤔 배운점

▸ 안드로이드 스튜디오의 라이브러리 중에 SharedPreferences라는 라이브러리를 새로 알게 되었고, 이후 프로젝트 진행 중에도 이번 같은 상황( DB를 쓰기엔 너무 작은 데이터를 저장해야할 때 )이 생기면 SharedPreferences를 사용하고 있다.

▸ 키보드 밖의 화면을 터치하면 키보드가 내려가는 것과 같은 사용자가 느낄 수 있는 자잘한 오류들의 존재를 알게 되었다.


