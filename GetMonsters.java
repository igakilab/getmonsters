import java.util.Scanner;

public class GetMonsters {
  double dt = 0.0;// 歩いた距離
  int balls = 5;// モンスターを捕まえられるボールの数
  int sub = 5;
  int hyb = 5;
  int p = 5;
  int c = 5;
  Scanner sc = new Scanner(System.in);

  // 卵は最大9個まで持てる．卵を取得するとeggにtrueが代入され，
  // 移動するたびに,eggDistanceに1.0kmずつ加算される．
  // 3km移動するとランダムでモンスターが孵る
  double eggDistance[] = new double[9];
  boolean egg[] = new boolean[9];

  // ユーザがGetしたモンスター一覧
  String userMonster[] = new String[100];

  // モンスター図鑑．モンスターの名前とレア度(0.0~9.0)がそれぞれの配列に保存されている
  // レア度が高いほうが捕まえにくい
  String monsterZukan[] = new String[22];
  double monsterRare[] = new double[22];
  String monsterTitle[] = new String[22];// 無し，超，絶，神の称号を保持する

  int pf = 0;
  int cf = 0;
  int bf = 0;
  int f1 = 0;
  int caf = 0;

  // 呼び出すと1km distanceが増える
  void move() {
    this.dt++;
    for (int i = 0; i < this.egg.length; i++) {// 卵は移動距離が進むと孵化するため，何km移動したかを更新する
      if (this.egg[i] == true) {
        this.eggDistance[i]++;
      }
    }

    f1 = (int) (Math.random() * 10 + 1);// 1,2の場合はMonStation，5以上の場合はモンスター
    if (f1 <= 2) {
      System.out.println("Monstationを見つけた！");
      int b = (int) (Math.random() * 4);// ball,fruits,eggがランダムに出る
      int sb = (int) (Math.random() * 3);
      int hb = (int) (Math.random() * 2);
      // int f = (int) (Math.random() * 3);
      int p = (int) (Math.random() * 3);
      int c = (int) (Math.random() * 3);
      int e = (int) (Math.random() * 3);
      System.out.println("モン玉を" + b + "個，"
          + "スーパーモン玉を" + sb + "個，"
          + "ハイパーモン玉を" + hb + "個，"
          + "ピーチを" + p + "個，"
          + "チェリーを" + c + "個，"
          + "卵を" + e + "個Getした！");
      this.balls = this.balls + b;
      this.sub += sb;
      this.hyb += hb;
      this.p += p;
      this.c += c;
      // this.fruits = this.fruits + f;
      if (e >= 1) {// 卵を1つ以上Getしたら
        // egg[]に10個以上卵がない場合は新しい卵データをセットする
        for (int i = 0; i < this.eggDistance.length; i++) {
          if (this.egg[i] == false) {
            this.egg[i] = true;
            this.eggDistance[i] = 0.0;
            break;
          }
        }
      }
    } else if (f1 >= 5) {
      int m = (int) (this.monsterZukan.length * Math.random());//
      // monsterZukanからランダムにモンスターを出す

      System.out.println("野生の" + this.monsterZukan[m] + "が現れた！");

      while (true) {

        System.out.println("何を投げるか下記の数字から選択だ！(括弧内は現在の所持数)");
        System.out.println("1.モン玉(" + this.balls +
            ")，2.スーパーモン玉(" + this.sub +
            ")，3.ハイパーモン玉(" + this.hyb +
            ")，4.ピーチ(" + this.p +
            ")，5.チェリー(" + this.c + ")");
        int throwObject = sc.nextInt();// 何を投げたか
        if (throwObject == 4) {
          if (this.p <= 0) {
            System.out.println("もうピーチは存在しない");
            System.out.println("まごまごしているうちにモンスターは逃げてしまった");
            break;
          }
          System.out.println("ピーチを投げた．モンスターを捕まえたら2段階進化するぞ");
          pf = 1;
          cf = 0;
          this.p--;
          continue;
        } else if (throwObject == 5) {
          if (this.c <= 0) {
            System.out.println("もうチェリーは存在しない");
            System.out.println("まごまごしているうちにモンスターは逃げてしまった");
            break;
          }
          System.out.println("チェリーを投げた．モンスターを捕まえやすくなるぞ");
          pf = 0;
          cf = 1;
          this.c--;
          continue;
        } else if (throwObject == 1) {
          if (this.balls <= 0) {
            System.out.println("もうモン玉は存在しない");
            System.out.println("まごまごしているうちにモンスターは逃げてしまった");
            break;
          }
          System.out.println(this.monsterZukan[m] + "にモン玉を投げた．");
          int r = (int) (5 * Math.random());// 0~4までの数字をランダムに返す
          if (cf == 1) {
            r = r + 2;
          }
          this.balls--;
          if (this.monsterRare[m] <= r) {// monsterRare[m]の値がr以下の場合
            System.out.println(this.monsterZukan[m] + "を捕まえた！");
            cf = 0;
            pf = 0;

            for (int j = 0; j < userMonster.length; j++) {
              if (this.userMonster[j] == this.monsterZukan[m]) {
                if (pf == 1) {
                  if (this.monsterTitle[j] == null) {
                    this.monsterTitle[j] = "絶";
                    System.out
                        .println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
                  } else if (this.monsterTitle[j] == "超") {
                    this.monsterTitle[j] = "神";
                    System.out
                        .println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
                  } else if (this.monsterTitle[j] == "絶") {
                    this.monsterTitle[j] = "神";
                    System.out
                        .println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
                  } else if (this.monsterTitle[j] == "神") {
                    System.out
                        .println(this.userMonster[j] + "は既に" + this.monsterTitle[j] + this.userMonster[j] + "に進化している");
                  }
                } else {
                  if (this.monsterTitle[j] == null) {
                    this.monsterTitle[j] = "超";
                    System.out
                        .println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
                  } else if (this.monsterTitle[j] == "超") {
                    this.monsterTitle[j] = "絶";
                    System.out
                        .println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
                  } else if (this.monsterTitle[j] == "絶") {
                    this.monsterTitle[j] = "神";
                    System.out
                        .println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
                  } else if (this.monsterTitle[j] == "神") {
                    System.out
                        .println(this.userMonster[j] + "は既に" + this.monsterTitle[j] + this.userMonster[j] + "に進化している");
                  }
                }
                break;
              } else if (this.userMonster[j] == null) {
                this.userMonster[j] = this.monsterZukan[m];
                break;
              }
            }
            break;
          } else {
            System.out.println("モンスターは逃げ出した");
            cf = 0;
            pf = 0;
            break;
          }

        } else if (throwObject == 2) {
          if (this.sub <= 0) {
            System.out.println("もうスーパーモン玉は存在しない");
            System.out.println("まごまごしているうちにモンスターは逃げてしまった");
            break;
          }

          System.out.println("スーパーモン玉を投げた．");
          int r = (int) (5 * Math.random() + 2);// 0~4までの数字をランダムに返す
          if (cf == 1) {
            r = r + 2;
          }
          this.sub--;
          if (this.monsterRare[m] <= r) {// monsterRare[m]の値がr以下の場合
            System.out.println(this.monsterZukan[m] + "を捕まえた！");
            cf = 0;
            pf = 0;

            for (int j = 0; j < userMonster.length; j++) {
              if (this.userMonster[j] == this.monsterZukan[m]) {
                if (pf == 1) {
                  if (this.monsterTitle[j] == null) {
                    this.monsterTitle[j] = "絶";
                    System.out
                        .println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
                  } else if (this.monsterTitle[j] == "超") {
                    this.monsterTitle[j] = "神";
                    System.out
                        .println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
                  } else if (this.monsterTitle[j] == "絶") {
                    this.monsterTitle[j] = "神";
                    System.out
                        .println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
                  } else if (this.monsterTitle[j] == "神") {
                    System.out
                        .println(this.userMonster[j] + "は既に" + this.monsterTitle[j] + this.userMonster[j] + "に進化している");
                  }
                } else {
                  if (this.monsterTitle[j] == null) {
                    this.monsterTitle[j] = "超";
                    System.out
                        .println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
                  } else if (this.monsterTitle[j] == "超") {
                    this.monsterTitle[j] = "絶";
                    System.out
                        .println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
                  } else if (this.monsterTitle[j] == "絶") {
                    this.monsterTitle[j] = "神";
                    System.out
                        .println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
                  } else if (this.monsterTitle[j] == "神") {
                    System.out
                        .println(this.userMonster[j] + "は既に" + this.monsterTitle[j] + this.userMonster[j] + "に進化している");
                  }
                }
                break;
              } else if (this.userMonster[j] == null) {
                this.userMonster[j] = this.monsterZukan[m];
                break;
              }
            }
            break;
          } else {
            System.out.println("モンスターはボールから抜け出した．再挑戦！");
            continue;
          }
        } else if (throwObject == 3) {
          if (this.hyb <= 0) {
            System.out.println("もうハイパーモン玉は存在しない");
            System.out.println("まごまごしているうちにモンスターは逃げてしまった");
            break;
          }

          System.out.println("ハイパーモン玉を投げた．");
          int r = (int) (5 * Math.random() + 3);// 0~4までの数字をランダムに返す
          if (cf == 1) {
            r = r + 2;
          }
          this.hyb--;
          if (this.monsterRare[m] <= r) {// monsterRare[m]の値がr以下の場合
            System.out.println(this.monsterZukan[m] + "を捕まえた！");
            cf = 0;
            pf = 0;

            for (int j = 0; j < userMonster.length; j++) {
              if (this.userMonster[j] == this.monsterZukan[m]) {
                if (pf == 1) {
                  if (this.monsterTitle[j] == null) {
                    this.monsterTitle[j] = "絶";
                    System.out
                        .println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
                  } else if (this.monsterTitle[j] == "超") {
                    this.monsterTitle[j] = "神";
                    System.out
                        .println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
                  } else if (this.monsterTitle[j] == "絶") {
                    this.monsterTitle[j] = "神";
                    System.out
                        .println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
                  } else if (this.monsterTitle[j] == "神") {
                    System.out
                        .println(this.userMonster[j] + "は既に" + this.monsterTitle[j] + this.userMonster[j] + "に進化している");
                  }
                } else {
                  if (this.monsterTitle[j] == null) {
                    this.monsterTitle[j] = "超";
                    System.out
                        .println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
                  } else if (this.monsterTitle[j] == "超") {
                    this.monsterTitle[j] = "絶";
                    System.out
                        .println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
                  } else if (this.monsterTitle[j] == "絶") {
                    this.monsterTitle[j] = "神";
                    System.out
                        .println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
                  } else if (this.monsterTitle[j] == "神") {
                    System.out
                        .println(this.userMonster[j] + "は既に" + this.monsterTitle[j] + this.userMonster[j] + "に進化している");
                  }
                }
                break;
              } else if (this.userMonster[j] == null) {
                this.userMonster[j] = this.monsterZukan[m];
                break;
              }
            }
            break;
          } else {
            System.out.println("モンスターはボールから抜け出した．再挑戦！");
            continue;
          }
        } else {
          System.out.println("何も投げられなかった．．．モンスターは逃げ出した");
          throwObject = 0;
          cf = 0;
          pf = 0;

          break;
        }
      }

    }
    for (int i = 0; i < this.egg.length; i++) {
      if (this.egg[i] == true && this.eggDistance[i] >= 3) {
        System.out.println("卵が孵った！");
        int m = (int) (this.monsterZukan.length * Math.random());
        System.out.println(this.monsterZukan[m] + "が産まれた！");

        for (int j = 0; j < userMonster.length; j++) {
          if (this.userMonster[j] == this.monsterZukan[m]) {
            if (this.monsterTitle[j] == null) {
              this.monsterTitle[j] = "超";
              System.out.println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
            } else if (this.monsterTitle[j] == "超") {
              this.monsterTitle[j] = "絶";
              System.out.println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
            } else if (this.monsterTitle[j] == "絶") {
              this.monsterTitle[j] = "神";
              System.out.println(this.userMonster[j] + "は" + this.monsterTitle[j] + this.userMonster[j] + "に進化した");
            } else if (this.monsterTitle[j] == "神") {
              System.out
                  .println(this.userMonster[j] + "は既に" + this.monsterTitle[j] + this.userMonster[j] + "に進化している");
            }
            break;
          } else if (this.userMonster[j] == null) {
            this.userMonster[j] = this.monsterZukan[m];
            break;
          }
        }
        this.egg[i] = false;
        this.eggDistance[i] = 0.0;
      }
    }
  }

  public double getDistance() {
    return dt;
  }

  public int getBalls() {
    return balls;
  }

  public String[] getUserMonster() {
    return userMonster;
  }

  public void setMonsterZukan(String[] monsterZukan) {
    this.monsterZukan = monsterZukan;
  }

  public void setMonsterRare(double[] monsterRare) {
    this.monsterRare = monsterRare;
  }

  public int getSuperBalls() {
    return sub;
  }

  public void setSuperBalls(int superBalls) {
    this.sub = superBalls;
  }

  public int getHyperBalls() {
    return hyb;
  }

  public void setHyperBalls(int hyperBalls) {
    this.hyb = hyperBalls;
  }

  public int getPeach() {
    return p;
  }

  public void setPeach(int peach) {
    this.p = peach;
  }

  public int getCherry() {
    return c;
  }

  public void setCherry(int cherry) {
    this.c = cherry;
  }

  public String[] getMonsterTitle() {
    return monsterTitle;
  }

  public void setMonsterTitle(String[] monsterTitle) {
    this.monsterTitle = monsterTitle;
  }

}
