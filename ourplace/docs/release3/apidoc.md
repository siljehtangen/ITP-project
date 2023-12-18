## API Endpoints

- `POST  /posts/add`
- `GET /posts/get`
- `DELETE /posts/delete`
- `PUT /posts/like`

## Post JSON Format
```
{
    "uuid": String,
    "creator": String,
    "header": String,
    "body": String,
    "date": String,
    "likes": int,
    "liked": boolean,
}
```
## Endpoints

### POST /posts/add
- About: Publish a post to Ourplace.
- Request body: 
    - Post JSON (see Post JSON Format)
- Returns: `ResponseEntity<Void>`

### GET /posts/get
- About Get all posts published on Ourplace.
- Returns `ResponseEntity<List<Post>>`

### DELETE /posts/delete
- About: Delete last post published to Ourplace.
- Returns `ResponseEntity<Void>`

### PUT /posts/like
- About: Like a post.
- Request params: 
    - `String postId`
- Returns `ResponseEntity<Void>`
