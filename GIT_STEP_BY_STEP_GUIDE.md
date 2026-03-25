# Huong Dan Tung Buoc Thao Tac Git (Cho Du An Java)

Tai lieu nay giup ban thao tac Git tu dau den cuoi theo luong bai tap:
- Tao repository
- Tao nhanh `exercise`
- Them code Java
- Push len GitHub
- Tao Pull Request vao `main`
- Moi collaborator

## 0. Dieu kien can
- Da cai `git`
- Da co tai khoan GitHub
- Dang o thu muc du an (vi du: `d:\WorkSpace\SideJob`)

---

## 1. Kiem tra thu muc hien tai
```powershell
cd d:\WorkSpace\SideJob
Get-ChildItem -Name
```
Muc tieu: chac chan ban dang dung thu muc du an.

## 2. Khoi tao Git repository
```powershell
git init
git branch -M main
```
- `git init`: tao repo Git moi
- `git branch -M main`: dat nhanh mac dinh la `main`

## 3. Cau hinh ten/email cho commit
```powershell
git config user.name "your-github-username"
git config user.email "your-email@gmail.com"
```
Neu muon dung cho moi repo tren may:
```powershell
git config --global user.name "your-github-username"
git config --global user.email "your-email@gmail.com"
```

## 4. Tao `.gitignore`
De bo qua file build Java (`.class`):
```powershell
Set-Content -Path .gitignore -Value "*.class"
```

## 5. Tao commit khoi tao tren `main`
```powershell
Set-Content -Path README.md -Value "# ten-repo`nMo ta ngan gon du an."
git add README.md .gitignore
git commit -m "Initialize main branch"
```

## 6. Tao nhanh bai tap `exercise`
```powershell
git checkout -b exercise
```

## 7. Them code Java va commit
```powershell
git add *.java .gitignore
git commit -m "Add Java Core code exercises"
```

Kiem tra nhanh:
```powershell
git status
git log --oneline --decorate --graph --all -n 10
```

## 8. Tao repository tren GitHub
Truy cap: `https://github.com/new`
- Name: vi du `java-core-exercies`
- Visibility: Public hoac Private
- Khong can them README neu ban da co local commit

## 9. Gan remote va push len GitHub
```powershell
git remote add origin https://github.com/<owner>/<repo>.git
git push -u origin main
git push -u origin exercise
```

Kiem tra:
```powershell
git remote -v
git branch -vv
```

## 10. Tao Pull Request vao `main`
Mo link:
`https://github.com/<owner>/<repo>/pull/new/exercise`

Sau do:
- Base: `main`
- Compare: `exercise`
- Bam `Create pull request`

## 11. Moi collaborator `@aliasflurry`
Mo:
`https://github.com/<owner>/<repo>/settings/access`

Thao tac:
- Bam `Invite collaborator`
- Nhap `aliasflurry`
- Gui loi moi

---

## Lenh hay dung hang ngay
```powershell
git status
git add .
git commit -m "Mo ta thay doi"
git push
```

## Khi can lay code moi nhat tu `main` vao `exercise`
```powershell
git checkout main
git pull origin main
git checkout exercise
git merge main
```

## Xu ly loi thuong gap
1. Loi "Please tell me who you are"
- Nguyen nhan: chua set `user.name`/`user.email`
- Cach sua: chay lai buoc 3

2. Loi "Repository not found"
- Nguyen nhan: repo GitHub chua tao hoac sai URL
- Cach sua: kiem tra repo ton tai va URL remote

3. Push bi tu choi do conflict
- Chay:
```powershell
git pull --rebase origin <branch>
git push
```

---

## Checklist nop bai
- [ ] Co branch `main`
- [ ] Co branch `exercise`
- [ ] Code Java nam trong `exercise`
- [ ] Da push len GitHub
- [ ] Da tao PR `exercise -> main`
- [ ] Da moi `@aliasflurry`
