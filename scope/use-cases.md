# Use-cases/Flows

## Login
### Flow
- User opens up the app (http://localhost:8080/highlights)
- User is presented with a login form
- Enters credentials
- Clicks Submit
- User logs in
- Taken to the Home page

### API/Handler
- Authentication handler

## Home page
### Flow
- Header + Main Section + Footer
- Main Section ==> Contents Ribbon (left) + highlights portlet (right)
- Content Ribbons ==> Recommended, Watch again
- Highlights portlet shows one highlight 
- Clicking on any content will lead the user to the content page
- Reloading the page shows a different highlight
### API/Handler
- GET - highlight

## Content Page
### Flow
- Main Section ==> content pane (left) + highlights pane (right, minimized)
- Clicking on each highlight will take the user directly to that text in the content
- Previously highlighted text will show up highlighted
### API/Handler
- GET - all highlights for this content

## Personalization
### Flow
- Change highlighting options
  - icon
  - decoration: background color, underline
### API/Handler
- GET - get highlight config
- GET - get highlight stylesheet for this user
- POST - save highlight config


